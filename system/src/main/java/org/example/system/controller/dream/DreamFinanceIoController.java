package org.example.system.controller.dream;


import com.baomidou.lock.annotation.Lock4j;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceIoService;
import org.example.service.dream.WalletService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.financeIo.DreamUFinanceIoReqVo;
import org.example.vo.dream.financeIo.DreamUFinanceIoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.operatelog.core.enums.OperateTypeEnum.UPDATE;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream finance io controller.
 */
@RestController
@RequestMapping("/dream/financeIo")
@Slf4j
public class DreamFinanceIoController {

    @Resource
    private DreamUFinanceIoService dreamUFinanceIoService;
    @Resource
    private WalletService walletService;


    /**
     * 查询订单
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "充提记录查询-分页", moduleName = "FinanceIo", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUFinanceIoVO>> getBannerPage(@Valid DreamUFinanceIoReqVo pageVO) {
        PageResult<DreamUFinanceIoVO> pageResult = dreamUFinanceIoService.pageByParams(pageVO);
        return success(pageResult);
    }


    /**
     * 操作 审核，支付
     *
     * @param pageVO the page vo
     * @return common result
     */
    @Lock4j(name = "lock4j_finance", keys = {"#pageVO.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "充提记录操作", moduleName = "FinanceIo", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("/statusOp")
    public CommonResult<Boolean> statusOp(@Valid @RequestBody DreamUFinanceIoReqVo pageVO) {
        return success(walletService.orderIoAudit(pageVO));
    }


}
