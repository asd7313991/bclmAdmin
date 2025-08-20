package org.example.system.db.mysql.po.system;

//import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
//import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
//import cn.iocoder.yudao.framework.mybatis.core.type.JsonLongSetTypeHandler;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.example.mybatis.type.JsonLongSetTypeHandler;
import org.example.po.BaseEntity;

import java.util.Set;

/**
 * 租户套餐 DO
 *
 * @author 后台源码
 */
@TableName(value = "system_tenant_package", autoResultMap = true)
@KeySequence("system_tenant_package_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TenantPackageDO extends BaseEntity {


    /**
     * 套餐名，唯一
     */
    private String name;
    /**
     * 租户套餐状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的菜单编号
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> menuIds;

}
