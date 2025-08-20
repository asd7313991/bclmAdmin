package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.po.system.LoginLogDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.system.logger.LoginLogService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.vo.system.loginlog.LoginLogExcelVO;
import org.example.vo.system.loginlog.LoginLogExportReqVO;
import org.example.vo.system.loginlog.LoginLogPageReqVO;
import org.example.vo.system.loginlog.LoginLogRespVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;


/**
 * The type Login log controller.
 */
@Tag(name = "管理后台 - 登录日志")
@RestController
@RequestMapping("/system/login-log")
@Validated
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Gets login log page.
     *
     * @param reqVO the req vo
     * @return the login log page
     */
    @GetMapping("/page")
//    @Operation(summary = "获得登录日志分页列表")
    @TLogAspectExt(str = "获得登录日志分页列表", moduleName = "AdminLoginLog", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    @SystemCheckPermission(value = "system:login-log:query")
    public CommonResult<PageResult<LoginLogRespVO>> getLoginLogPage(@Valid LoginLogPageReqVO reqVO) {
        PageResult<LoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        PageResult<LoginLogRespVO> empty = PageResult.empty();
        empty.setTotal(page.getTotal());
        empty.setList(mapperFacade.mapAsList(page.getList(), LoginLogRespVO.class));
        return CommonResult.success(empty);
    }

    /**
     * Export login log.
     *
     * @param response the response
     * @param reqVO    the req vo
     * @throws IOException the io exception
     */
    @GetMapping("/export")
//    @Operation(summary = "导出登录日志 Excel")
    @SystemCheckPermission(value = "system:login-log:export")
//    @OperateLog(type = EXPORT)
    @TLogAspectExt(str = "导出登录日志", moduleName = "AdminLoginLog", type = EXPORT, convert = SystemTLogConvert.class)
    public void exportLoginLog(HttpServletResponse response, @Valid LoginLogExportReqVO reqVO) throws IOException {
        List<LoginLogDO> list = loginLogService.getLoginLogList(reqVO);
        // 拼接数据
        List<LoginLogExcelVO> data = mapperFacade.mapAsList(list, LoginLogExcelVO.class);
        // 输出
        ExcelUtils.write(response, "登录日志.xls", "数据列表", LoginLogExcelVO.class, data);
    }

}
