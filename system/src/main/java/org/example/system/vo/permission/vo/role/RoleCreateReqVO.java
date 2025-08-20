package org.example.system.vo.permission.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Role create req vo.
 */
@Schema(description = "管理后台 - 角色创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleCreateReqVO extends RoleBaseVO {

    /**
     * 多租户编号
     */
    private Long tenantId;
}
