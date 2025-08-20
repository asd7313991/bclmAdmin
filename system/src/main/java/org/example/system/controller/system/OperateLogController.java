package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.glasnost.orika.MapperFacade;
import org.example.excel.core.util.ExcelUtils;
import org.example.po.system.OperateLogDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.system.logger.OperateLogService;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.system.user.AdminUserService;
import org.example.util.collection.CollectionUtils;
import org.example.util.collection.MapUtils;
import org.example.vo.system.operatelog.OperateLogExcelVO;
import org.example.vo.system.operatelog.OperateLogExportReqVO;
import org.example.vo.system.operatelog.OperateLogPageReqVO;
import org.example.vo.system.operatelog.OperateLogRespVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.example.pojo.CommonResult.success;


/**
 * The type Operate log controller.
 */
@Tag(name = "管理后台 - 操作日志")
@RestController
@RequestMapping("/system/operate-log")
@Validated
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;
    @Resource
    private AdminUserService userService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Page operate log common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @GetMapping("/page")
//    @Operation(summary = "查看操作日志分页列表")
    @SystemCheckPermission(value = "system:operate-log:query")
    public CommonResult<PageResult<OperateLogRespVO>> pageOperateLog(@Valid OperateLogPageReqVO reqVO) {
        PageResult<OperateLogDO> pageResult = operateLogService.getOperateLogPage(reqVO);

        // 获得拼接需要的数据
        Collection<Long> userIds = CollectionUtils.convertList(pageResult.getList(), OperateLogDO::getUserId);
        Map<Long, AdminUserDO> userMap = userService.getUserMap(userIds);
        // 拼接数据
        List<OperateLogRespVO> list = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(operateLog -> {
            OperateLogRespVO respVO = mapperFacade.map(operateLog, OperateLogRespVO.class);
            list.add(respVO);
            // 拼接用户信息
            MapUtils.findAndThen(userMap, operateLog.getUserId(), user -> respVO.setUserNickname(user.getNickname()));
        });
        return success(new PageResult<>(list, pageResult.getTotal()));
    }

    /**
     * Export operate log.
     *
     * @param response the response
     * @param reqVO    the req vo
     * @throws IOException the io exception
     */
    @Operation(summary = "导出操作日志")
    @GetMapping("/export")
    @SystemCheckPermission(value = "system:operate-log:export")
//    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportOperateLog(HttpServletResponse response, @Valid OperateLogExportReqVO reqVO) throws IOException {
        List<OperateLogDO> list = operateLogService.getOperateLogList(reqVO);
        // 获得拼接需要的数据
        Collection<Long> userIds = CollectionUtils.convertList(list, OperateLogDO::getUserId);
        Map<Long, AdminUserDO> userMap = userService.getUserMap(userIds);
        // 拼接数据
        List<OperateLogExcelVO> excelDataList = null;
        // 输出
        ExcelUtils.write(response, "操作日志.xls", "数据列表", OperateLogExcelVO.class, excelDataList);
    }

}
