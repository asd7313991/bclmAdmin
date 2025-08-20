package org.example.system.db.mysql.po.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.BaseEntity;

/**
 * The type Tenant base do.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class TenantBaseDO extends BaseEntity {

    /**
     * 多租户编号
     */
    private Long tenantId;

}
