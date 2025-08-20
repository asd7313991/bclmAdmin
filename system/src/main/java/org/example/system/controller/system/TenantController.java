package org.example.system.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.db.mysql.po.system.TenantDO;
import org.example.system.system.tenant.TenantService;
import org.example.system.vo.tenant.vo.tenant.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;


/**
 * The type Tenant controller.
 */
@Tag(name = "管理后台 - 租户")
@RestController
@RequestMapping("/system/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Gets tenant id by name.
     *
     * @param name the name
     * @return the tenant id by name
     */
    @GetMapping("/get-id-by-name")
    @SaIgnore
    @TLogAspectExt(str = "获得租户编号", moduleName = "tenant", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<Long> getTenantIdByName(@RequestParam("name") String name) {
        TenantDO tenantDO = tenantService.getTenantByName(name);
        return success(tenantDO != null ? tenantDO.getId() : null);
    }

    /**
     * Create tenant common result.
     *
     * @param createReqVO the create req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:tenant:create")
    @TLogAspectExt(str = "创建租户", moduleName = "tenant", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createTenant(@Valid @RequestBody TenantCreateReqVO createReqVO) {
        return success(tenantService.createTenant(createReqVO));
    }

    /**
     * Update tenant common result.
     *
     * @param updateReqVO the update req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:tenant:update")
    @TLogAspectExt(str = "更新租户", moduleName = "tenant", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateTenant(@Valid @RequestBody TenantUpdateReqVO updateReqVO) {
        tenantService.updateTenant(updateReqVO);
        return success(true);
    }

    /**
     * Delete tenant common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:tenant:delete")
    @TLogAspectExt(str = "删除租户", moduleName = "tenant", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteTenant(@RequestParam("id") Long id) {
        tenantService.deleteTenant(id);
        return success(true);
    }

    /**
     * Gets tenant.
     *
     * @param id the id
     * @return the tenant
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:tenant:query")
    @TLogAspectExt(str = "获得租户", moduleName = "tenant", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<TenantRespVO> getTenant(@RequestParam("id") Long id) {
        TenantDO tenant = tenantService.getTenant(id);
        return success(mapperFacade.map(tenant, TenantRespVO.class));
    }

    /**
     * Gets tenant page.
     *
     * @param pageVO the page vo
     * @return the tenant page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:tenant:query")
    @TLogAspectExt(str = "获得租户分页", moduleName = "tenant", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<TenantRespVO>> getTenantPage(@Valid TenantPageReqVO pageVO) {
        PageResult<TenantDO> pageResult = tenantService.getTenantPage(pageVO);
        List<TenantDO> list = pageResult.getList();
        PageResult<TenantRespVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(list, TenantRespVO.class));
        return success(empty);
    }

    /**
     * Export tenant excel.
     *
     * @param exportReqVO the export req vo
     * @param response    the response
     * @throws IOException the io exception
     */
    @GetMapping("/export-excel")
    @SystemCheckPermission(value = "system:tenant:export")
    @TLogAspectExt(str = "导出租户", moduleName = "tenant", type = EXPORT)
    public void exportTenantExcel(@Valid TenantExportReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        List<TenantDO> list = tenantService.getTenantList(exportReqVO);
        // 导出 Excel
        List<TenantExcelVO> datas = mapperFacade.mapAsList(list, TenantExcelVO.class);
        ExcelUtils.write(response, "租户.xls", "数据", TenantExcelVO.class, datas);
    }


}
