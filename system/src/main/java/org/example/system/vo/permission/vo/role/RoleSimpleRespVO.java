package org.example.system.vo.permission.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Role simple resp vo.
 */
@Schema(description = "管理后台 - 角色精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSimpleRespVO {

    @Schema(description = "角色编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    private String code;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "后台")
    private String name;

}
