# 背景

* **服务/模块**：`game`（定时任务） + 相关 `service/mapper`（可能位于 `service.hf` 包）
* **相关功能**：生成/结算期号（issue）任务
* **首次发现**：2025-08-21 01:34（你的日志时间）

> 关键日志摘要：

```
2025-08-21 01:34:14.032 ... ERROR org.example.game.game.task.GameIssueTask - getIssueNum [null] for error[[
org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate ...
org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible ...
com.baomidou.mybatisplus.core.override.MybatisMapperProxy$PlainMethodInvoker.invoke ...
org.example.service.hf.impl.HfGameIssueServiceImpl.drawGameIssue(HfGameIssueServiceImpl.java:
```

---

## 现象 & 影响

* **现象**：`GameIssueTask` 在执行期间触发数据库异常（多见于 `insert`/`update`），任务失败或反复重试。
* **影响**：

    * 新期号未正确写入，后续玩法/开奖链路受阻。
    * 若无幂等保护，可能产生重复插入/唯一键冲突日志噪音。

---

## 高概率根因（按命中率排序）

1. **Mapper XML ↔ 表结构不一致**：缺列、非空字段未赋值、字段长度/精度对不上。
2. **主键策略未声明**：数据库是自增，但实体/XML 未配置 `useGeneratedKeys` 或 `@TableId(IdType.AUTO)`。
3. **枚举/状态字段未注册 TypeHandler**：如 `BattleType`、`IssueStatus` 等。
4. **多数据源路由/事务声明不当**：写操作走了只读库或 `@Transactional(readOnly=true)` 导致失败。
5. **时间/时区/精度**：`LocalDateTime` 与 `datetime(3)`/`timestamp` 映射不匹配或驱动过旧。

---

## 快速复现 & 定位

1. **只跑这条任务**（避免其他噪音）：

    * 临时在 `GameIssueTask` 上加一个只触发 `drawGameIssue()` 的测试入口，或手动调用服务层方法。
2. **打开 SQL 日志**（任一相关服务的 `application-*.yml`）：

```yaml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

3. **抓 SQL 与参数**：对照数据库表结构核验每一列是否匹配（类型、非空、默认值、长度）。

---

## 修复清单（Checklist）

* [ ] **表结构核对**：`INSERT` 涉及的每个字段在表中存在；非空列有值或有默认值；`varchar/decimal` 长度精度足够。
* [ ] **主键策略**：

    * 实体：`@TableId(type = IdType.AUTO)`（或合适的 `ASSIGN_ID/INPUT`）。
    * XML：`useGeneratedKeys="true" keyProperty="id"`。
* [ ] **枚举映射**：

    * 注册全局 `TypeHandler`（`type-handlers-package`），或在枚举上用 `@EnumValue`。
    * 列类型与枚举存储形式一致（`varchar`/`int`）。
* [ ] **数据源与事务**：写方法不带 `readOnly=true`；动态数据源路由到 `master`。
* [ ] **时间字段**：MySQL 8 驱动（≥8.0.28）；`datetime(3)` ↔ `LocalDateTime`；统一时区参数。
* [ ] **幂等/锁**：为“期号”生成逻辑加唯一键/分布式锁，避免并发重复。

---

## 代码片段模板（可直接套用）

### 1) 枚举 TypeHandler（按需）

```java
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({BattleType.class, IssueStatus.class})
public class EnumCodeTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {
  private final Class<E> type;
  public EnumCodeTypeHandler(Class<E> type) { this.type = type; }
  @Override public void setNonNullParameter(PreparedStatement ps, int i, E param, JdbcType jdbcType) throws SQLException {
    ps.setString(i, param.getCode()); // CodeEnum#getCode()
  }
  @Override public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String code = rs.getString(columnName);
    return CodeEnum.fromCode(type, code);
  }
  @Override public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String code = rs.getString(columnIndex);
    return CodeEnum.fromCode(type, code);
  }
  @Override public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String code = cs.getString(columnIndex);
    return CodeEnum.fromCode(type, code);
  }
}
```

**`application-*.yml`**：

```yaml
mybatis-plus:
  type-aliases-package: org.example.**.dal.dataobject
  mapper-locations: classpath*:mapper/**/*.xml
  type-handlers-package: org.example.common.mybatis.handler
```

### 2) XML 主键策略（示例）

```xml
<insert id="insertIssue" useGeneratedKeys="true" keyProperty="id">
  INSERT INTO game_issue
  ( issue_no, type, status, created_at )
  VALUES
  ( #{issueNo}, #{type}, #{status}, NOW() )
</insert>
```

### 3) 幂等保护（MySQL 示例）

```xml
<insert id="insertIssueOnDup">
  INSERT INTO game_issue (issue_no, type, status, created_at)
  VALUES (#{issueNo}, #{type}, #{status}, NOW())
  ON DUPLICATE KEY UPDATE updated_at = NOW()
</insert>
```

> 注意：需要给 `issue_no` 建唯一索引。

---

## 配置要点

* **数据源**：

```yaml
spring:
  datasource:
    dynamic:
      primary: master
```

* **MySQL 驱动 & 时区**：

    * 连接串追加 `serverTimezone=Asia/Dubai&useUnicode=true&characterEncoding=UTF-8`（按你的部署时区调整）。

---

## 验证步骤

1. 打开 SQL 日志，手工触发一次 `drawGameIssue()`。
2. 观察实际执行的 `INSERT` 与参数；对照表结构确认成功写入且主键返回正确。
3. 压测/并发测试，验证幂等与唯一键约束无误。
4. 观察任务下次调度是否稳定，不再出现相同堆栈。

---

## 回归与观察

* **回归点**：

    * 其他依赖 `Issue` 的读写接口是否受影响；
    * 定时任务的失败告警是否清零。
* **日志/监控**：

    * 为 `GameIssueTask` 增加关键埋点：开始/结束/耗时/入参摘要/生成的 `issue_no`。
    * 失败重试次数、唯一键冲突计数暴露到监控。

---

## 预防建议

* 在 CI 加入 **`mybatis-mapper-validator`** 这类静态校验（可自建规则），对照表结构校验 XML。
* 为关键表补齐 **NOT NULL + 默认值** 与 **唯一索引**。
* 为定时任务统一实现 **分布式锁**（如基于 Redis 的 `SET NX EX`）。

---

### 待你确认/补齐的材料

* 相关 **Mapper XML**、**DO/Entity**、**表结构** 的具体路径或片段。
* 是否存在 **多数据源** 与 **读写分离**，以及 `@Transactional` 配置。
* `GameIssueTask` 的触发策略（Cron）与并发策略（是否并发执行）。
