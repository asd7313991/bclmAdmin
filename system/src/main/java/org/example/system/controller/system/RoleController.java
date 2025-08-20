package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.permission.RoleConvert;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.system.permission.RoleService;
import org.example.system.vo.permission.vo.role.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.singleton;
import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;

/**
 * The type Role controller.
 */
@Tag(name = "管理后台 - 角色")
@RestController
@RequestMapping("/system/role")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * Create role common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:role:create")
    @TLogAspectExt(str = "创建角色", moduleName = "role", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createRole(@Valid @RequestBody RoleCreateReqVO reqVO) {
        return success(roleService.createRole(reqVO, null));
    }

    /**
     * Update role common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:role:update")
    @TLogAspectExt(str = "修改角色", moduleName = "role", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateRole(@Valid @RequestBody RoleUpdateReqVO reqVO) {
        roleService.updateRole(reqVO);
        return success(true);
    }

    /**
     * Update role status common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update-status")
    @SystemCheckPermission(value = "system:role:update")
    @TLogAspectExt(str = "修改角色状态", moduleName = "role", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody RoleUpdateStatusReqVO reqVO) {
        roleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * Delete role common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:role:delete")
    @TLogAspectExt(str = "删除角色", moduleName = "role", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return success(true);
    }

    /**
     * Gets role.
     *
     * @param id the id
     * @return the role
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:role:query")
    @TLogAspectExt(str = "创建角色", moduleName = "role", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<RoleRespVO> getRole(@RequestParam("id") Long id) {
        RoleDO role = roleService.getRole(id);
        return success(RoleConvert.INSTANCE.convert(role));
    }

    /**
     * Gets role page.
     *
     * @param reqVO the req vo
     * @return the role page
     */
    @GetMapping("/page")
    @Operation(summary = "获得角色分页")
    @SystemCheckPermission(value = "system:role:query")
    @TLogAspectExt(str = "获得角色分页", moduleName = "role", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<RoleDO>> getRolePage(RolePageReqVO reqVO) {
        return success(roleService.getRolePage(reqVO));
    }

    /**
     * Gets simple role list.
     *
     * @return the simple role list
     */
    @GetMapping("/list-all-simple")
    @TLogAspectExt(str = "获取角色精简信息列表", moduleName = "role", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<RoleSimpleRespVO>> getSimpleRoleList() {
        // 获得角色列表，只要开启状态的
        List<RoleDO> list = roleService.getRoleListByStatus(singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回给前端
        list.sort(Comparator.comparing(RoleDO::getSort));
        return success(RoleConvert.INSTANCE.convertList02(list));
    }

    /**
     * Export.
     *
     * @param response the response
     * @param reqVO    the req vo
     * @throws IOException the io exception
     */
    @GetMapping("/export")
    @SystemCheckPermission(value = "system:role:export")
    @TLogAspectExt(str = "导出", moduleName = "role", type = EXPORT)
    public void export(HttpServletResponse response, @Validated RoleExportReqVO reqVO) throws IOException {
        List<RoleDO> list = roleService.getRoleList(reqVO);
        List<RoleExcelVO> data = RoleConvert.INSTANCE.convertList03(list);
        // 输出
        ExcelUtils.write(response, "角色数据.xls", "角色列表", RoleExcelVO.class, data);
    }

}
