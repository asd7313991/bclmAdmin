package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowReqVo;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream finance flow controller.
 */
@RestController
@RequestMapping("/dream/financeFlow")
@Slf4j
public class DreamFinanceFlowController {

    @Resource
    private DreamUFinanceFlowService dreamUFinanceFlowService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 资金流水记录查询-分页
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "资金流水记录查询-分页", moduleName = "FinanceFlow", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUFinanceFlowVO>> getBannerPage(@Valid DreamUFinanceFlowReqVo pageVO) {
        PageResult<DreamUFinanceFlowVO> pageResult = dreamUFinanceFlowService.pageByParams(pageVO);
        return success(pageResult);
    }


    /**
     * 资金流水记录查询
     *
     * @param pageVO the page vo
     * @return common result
     */
    @TLogAspectExt(str = "资金流水记录查询", moduleName = "FinanceFlow", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/list")
    public CommonResult<List<DreamUFinanceFlowVO>> list(@Valid DreamUFinanceFlowReqVo pageVO) {
        List<DreamUFinanceFlowVO> pageResult = dreamUFinanceFlowService.listByParams(pageVO);
        return success(pageResult);
    }
}
