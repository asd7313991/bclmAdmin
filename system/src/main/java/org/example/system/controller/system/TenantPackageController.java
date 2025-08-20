package org.example.system.controller.system;


import io.swagger.v3.oas.annotations.tags.Tag;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.db.mysql.po.system.TenantPackageDO;
import org.example.system.system.tenant.TenantPackageService;
import org.example.system.vo.tenant.vo.packages.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.example.pojo.CommonResult.success;


/**
 * The type Tenant package controller.
 */
@Tag(name = "管理后台 - 租户套餐")
@RestController
@RequestMapping("/system/tenant-package")
@Validated
public class TenantPackageController {

    @Resource
    private TenantPackageService tenantPackageService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Create tenant package common result.
     *
     * @param createReqVO the create req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:tenant-package:create")
    @TLogAspectExt(str = "创建租户套餐", moduleName = "tenant-package", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createTenantPackage(@Valid @RequestBody TenantPackageCreateReqVO createReqVO) {
        return success(tenantPackageService.createTenantPackage(createReqVO));
    }

    /**
     * Update tenant package common result.
     *
     * @param updateReqVO the update req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:tenant-package:update")
    @TLogAspectExt(str = "更新租户套餐", moduleName = "tenant-package", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateTenantPackage(@Valid @RequestBody TenantPackageUpdateReqVO updateReqVO) {
        tenantPackageService.updateTenantPackage(updateReqVO);
        return success(true);
    }

    /**
     * Delete tenant package common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:tenant-package:delete")
    @TLogAspectExt(str = "删除租户套餐", moduleName = "tenant-package", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteTenantPackage(@RequestParam("id") Long id) {
        tenantPackageService.deleteTenantPackage(id);
        return success(true);
    }

    /**
     * Gets tenant package.
     *
     * @param id the id
     * @return the tenant package
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:tenant-package:query")
    @TLogAspectExt(str = "获得租户套餐", moduleName = "tenant-package", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<TenantPackageRespVO> getTenantPackage(@RequestParam("id") Long id) {
        TenantPackageDO tenantPackage = tenantPackageService.getTenantPackage(id);
        return success(mapperFacade.map(tenantPackage, TenantPackageRespVO.class));
    }

    /**
     * Gets tenant package page.
     *
     * @param pageVO the page vo
     * @return the tenant package page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:tenant-package:query")
    @TLogAspectExt(str = "获得租户套餐分页", moduleName = "tenant-package", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<TenantPackageRespVO>> getTenantPackagePage(@Valid TenantPackagePageReqVO pageVO) {
        PageResult<TenantPackageDO> pageResult = tenantPackageService.getTenantPackagePage(pageVO);
        PageResult<TenantPackageRespVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), TenantPackageRespVO.class));
        return success(empty);
    }

    /**
     * Gets tenant package list.
     *
     * @return the tenant package list
     */
    @GetMapping("/get-simple-list")
    @TLogAspectExt(str = "获取租户套餐精简信息列表", moduleName = "tenant-package", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<TenantPackageSimpleRespVO>> getTenantPackageList() {
        // 获得角色列表，只要开启状态的
        List<TenantPackageDO> list = tenantPackageService.getTenantPackageListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(mapperFacade.mapAsList(list, TenantPackageSimpleRespVO.class));
    }

}
