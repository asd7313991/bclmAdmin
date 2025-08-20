package org.example.system.vo.permission.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Menu create req vo.
 */
@Schema(description = "管理后台 - 菜单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuCreateReqVO extends MenuBaseVO {
}
