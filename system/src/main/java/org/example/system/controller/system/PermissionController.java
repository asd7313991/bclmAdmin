package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.system.permission.PermissionService;
import org.example.system.vo.permission.vo.permission.PermissionAssignRoleDataScopeReqVO;
import org.example.system.vo.permission.vo.permission.PermissionAssignRoleMenuReqVO;
import org.example.system.vo.permission.vo.permission.PermissionAssignUserRoleReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;

import static org.example.pojo.CommonResult.success;


/**
 * 权限 Controller，提供赋予用户、角色的权限的 API 接口
 *
 * @author 后台源码
 */
@Tag(name = "管理后台 - 权限")
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;
//    @Resource
//    private TenantService tenantService;

    /**
     * Gets role menu list.
     *
     * @param roleId the role id
     * @return the role menu list
     */
    @GetMapping("/list-role-menus")
    @SystemCheckPermission(value = "system:permission:assign-role-menu")
    @TLogAspectExt(str = "获得角色拥有的菜单编号", moduleName = "permission", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<Set<Long>> getRoleMenuList(Long roleId) {
        return success(permissionService.getRoleMenuListByRoleId(roleId));
    }

    /**
     * Assign role menu common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/assign-role-menu")
    @SystemCheckPermission(value = "system:permission:assign-role-menu")
    @TLogAspectExt(str = "赋予角色菜单", moduleName = "permission", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> assignRoleMenu(@Validated @RequestBody PermissionAssignRoleMenuReqVO reqVO) {
        // 开启多租户的情况下，需要过滤掉未开通的菜单
//        tenantService.handleTenantMenu(menuIds -> reqVO.getMenuIds().removeIf(menuId -> !CollUtil.contains(menuIds, menuId)));

        // 执行菜单的分配
        permissionService.assignRoleMenu(reqVO.getRoleId(), reqVO.getMenuIds());
        return success(true);
    }

    /**
     * Assign role data scope common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/assign-role-data-scope")
    @SystemCheckPermission(value = "system:permission:assign-role-data-scope")
    @TLogAspectExt(str = "赋予角色数据权限", moduleName = "permission", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> assignRoleDataScope(@Valid @RequestBody PermissionAssignRoleDataScopeReqVO reqVO) {
        permissionService.assignRoleDataScope(reqVO.getRoleId(), reqVO.getDataScope(), reqVO.getDataScopeDeptIds());
        return success(true);
    }

    /**
     * List admin roles common result.
     *
     * @param userId the user id
     * @return the common result
     */
    @GetMapping("/list-user-roles")
    @SystemCheckPermission(value = "system:permission:assign-user-role")
    @TLogAspectExt(str = "获得管理员拥有的角色编号列表", moduleName = "permission", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<Set<Long>> listAdminRoles(@RequestParam("userId") Long userId) {
        return success(permissionService.getUserRoleIdListByUserId(userId));
    }

    /**
     * Assign user role common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/assign-user-role")
    @SystemCheckPermission(value = "system:permission:assign-user-role")
    @TLogAspectExt(str = "赋予用户角色", moduleName = "permission", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> assignUserRole(@Validated @RequestBody PermissionAssignUserRoleReqVO reqVO) {
        permissionService.assignUserRole(reqVO.getUserId(), reqVO.getRoleIds());
        return success(true);
    }

}
