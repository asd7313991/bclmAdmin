package org.example.properties.tenant;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * 拓展多租户的 BaseDO 基类
 *
 * @author 后台源码
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseEntity {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
