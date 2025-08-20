package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.financeMain.DreamUFinancePageReqVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream finance main controller.
 */
@RestController
@RequestMapping("/dream/financeMain")
@Slf4j
public class DreamFinanceMainController {

    @Resource
    private DreamUFinanceMainService dreamUFinanceMainService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 用户资金记录查询
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "用户资金记录查询", moduleName = "FinanceMain", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUFinanceMainVO>> getBannerPage(@Valid DreamUFinancePageReqVO pageVO) {
        PageResult<DreamUFinanceMainVO> pageResult = dreamUFinanceMainService.pageByParams(pageVO);

        return success(pageResult);
    }
}
