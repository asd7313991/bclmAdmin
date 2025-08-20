package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.errorcode.ErrorCodeConvert;
import org.example.system.db.mysql.po.system.ErrorCodeDO;
import org.example.system.system.errorcode.ErrorCodeService;
import org.example.system.vo.errorcode.vo.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;

/**
 * The type Error code controller.
 */
@Tag(name = "管理后台 - 错误码")
@RestController
@RequestMapping("/system/error-code")
@Validated
public class ErrorCodeController {

    @Resource
    private ErrorCodeService errorCodeService;

    /**
     * Create error code common result.
     *
     * @param createReqVO the create req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:error-code:create")
    @TLogAspectExt(str = "创建错误码", moduleName = "error-code", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createErrorCode(@Valid @RequestBody ErrorCodeCreateReqVO createReqVO) {
        return success(errorCodeService.createErrorCode(createReqVO));
    }

    /**
     * Update error code common result.
     *
     * @param updateReqVO the update req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:error-code:update")
    @TLogAspectExt(str = "更新错误码", moduleName = "error-code", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateErrorCode(@Valid @RequestBody ErrorCodeUpdateReqVO updateReqVO) {
        errorCodeService.updateErrorCode(updateReqVO);
        return success(true);
    }

    /**
     * Delete error code common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:error-code:delete")
    @TLogAspectExt(str = "删除错误码", moduleName = "error-code", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteErrorCode(@RequestParam("id") Long id) {
        errorCodeService.deleteErrorCode(id);
        return success(true);
    }

    /**
     * Gets error code.
     *
     * @param id the id
     * @return the error code
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:error-code:query")
    @TLogAspectExt(str = "获得错误码", moduleName = "error-code", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<ErrorCodeRespVO> getErrorCode(@RequestParam("id") Long id) {
        ErrorCodeDO errorCode = errorCodeService.getErrorCode(id);
        return success(ErrorCodeConvert.INSTANCE.convert(errorCode));
    }

    /**
     * Gets error code page.
     *
     * @param pageVO the page vo
     * @return the error code page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:error-code:query")
    @TLogAspectExt(str = "获得错误码分页", moduleName = "error-code", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<ErrorCodeRespVO>> getErrorCodePage(@Valid ErrorCodePageReqVO pageVO) {
        PageResult<ErrorCodeDO> pageResult = errorCodeService.getErrorCodePage(pageVO);
        return success(ErrorCodeConvert.INSTANCE.convertPage(pageResult));
    }

    /**
     * Export error code excel.
     *
     * @param exportReqVO the export req vo
     * @param response    the response
     * @throws IOException the io exception
     */
    @GetMapping("/export-excel")
    @SystemCheckPermission(value = "system:error-code:export")
    @TLogAspectExt(str = "导出错误码", moduleName = "error-code", type = EXPORT)
    public void exportErrorCodeExcel(@Valid ErrorCodeExportReqVO exportReqVO,
                                     HttpServletResponse response) throws IOException {
        List<ErrorCodeDO> list = errorCodeService.getErrorCodeList(exportReqVO);
        // 导出 Excel
        List<ErrorCodeExcelVO> datas = ErrorCodeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "错误码.xls", "数据", ErrorCodeExcelVO.class, datas);
    }

}
