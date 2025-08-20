package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceDepositService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositPageReqVO;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream finance deposit controller.
 */
@RestController
@RequestMapping("/dream/financeDeposit")
@Slf4j
public class DreamFinanceDepositController {

    @Resource
    private DreamUFinanceDepositService dreamUFinanceDepositService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 充值记录查询
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "充值记录查询", moduleName = "FinanceDeposit", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUFinanceDepositVO>> getBannerPage(@Valid @RequestBody DreamUFinanceDepositPageReqVO pageVO) {
        PageResult<DreamUFinanceDepositVO> pageResult = dreamUFinanceDepositService.pageByParams(pageVO);

        return success(pageResult);
    }
}
