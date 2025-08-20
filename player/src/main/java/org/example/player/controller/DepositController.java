package org.example.player.controller;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamDepositTypeService;
import org.example.service.dream.WalletService;
import org.example.vo.DepositOrderReq;
import org.example.vo.dream.deposit.DepositTypeVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/player/deposit")
public class DepositController {
    @Resource
    private DreamDepositTypeService depositTypeService;
    @Resource
    private WalletService walletService;

    /**
     *发起充值订单
     */
    @TLogAspectExt(str = "发起充值", moduleName = "Deposit", type = OperateTypeEnum.CREATE, convert = PlayerLogConvert.class)
    @PostMapping("/submitDepositOrder")
    public CommonResult<Boolean> submitDeposit(@RequestBody DepositOrderReq depositOrderReq) {
        log.info("开始充值：{}", JSONUtil.toJsonStr(depositOrderReq));
        return CommonResult.success(walletService.deposit(depositOrderReq, StpPlayerUtil.getLoginIdAsLong()));
    }


    /**
     * 获取充值方式
     * @return
     */
    @TLogAspectExt(str = "获取充值方式", moduleName = "Deposit", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getDepositMethod")
    public CommonResult<List<DepositTypeVO>> getDepositMethod(){
        List<DepositTypeVO> depositTypeVOList =depositTypeService.getDepositMethod();
        return CommonResult.success(depositTypeVOList);
    }

}
