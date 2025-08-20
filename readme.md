1. common 模块（344 个 Java 文件）

作用：公共工具与配置

示例文件：

JacksonConfig.java → JSON 序列化配置

RedisConfiguration.java → Redis 配置

TLogAspect.java / TroubleshootingLogAspect.java → 日志 AOP 处理

特点：提供各种 公共组件、配置、工具类，被其他模块依赖。

2. system 模块（287 个 Java 文件）

作用：系统管理核心

示例文件：

SystemApplication.java → 入口启动类

DataPermission.java / DataPermissionAutoConfiguration.java → 数据权限管理

DataPermissionDatabaseInterceptor.java → 数据库访问权限拦截

特点：这里应该是 后台系统功能模块，负责权限、配置管理，可能是管理端服务。

3. player 模块（30 个 Java 文件）

作用：玩家相关服务

示例文件：

PlayerApplication.java → 独立入口启动类

PlayerErrorCodeConstants.java → 玩家相关错误码

RedisConfig.java → 玩家服务使用的 Redis 配置

PlayerLogConvert.java → 玩家日志转换

特点：专门处理 玩家业务逻辑，比如账号、角色数据。

4. game 模块（33 个 Java 文件）

作用：游戏业务逻辑

示例文件：

GameApplication.java → 独立入口启动类

SaTokenConfigure.java → 登录/认证配置（使用 Sa-Token 框架）

BattleLock.java / BattleRedisKey.java / BattleType.java → 战斗相关的常量

特点：核心 游戏玩法逻辑（战斗、对局等）。

✅ 总结：

common → 公共支撑模块（工具、配置）

system → 管理系统模块（权限、后台管理）

player → 玩家模块（用户、角色、日志）

game → 游戏逻辑模块（战斗、玩法、认证）

这 4 个模块通过 Maven 多模块组织，最终可以组合成一个完整的 Spring Boot 游戏后台项目。# feiliu28
