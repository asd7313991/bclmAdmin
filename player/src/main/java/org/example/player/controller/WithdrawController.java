package org.example.player.controller;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamWithdrawTypeService;
import org.example.service.dream.WalletService;
import org.example.vo.WithdrawOrderReq;
import org.example.vo.dream.withdraw.WithdrawTypeVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.example.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@RestController
@RequestMapping("/player/withdraw")
public class WithdrawController {


    @Resource
    private WalletService walletService;
    @Resource
    private DreamWithdrawTypeService dreamWithdrawTypeService;



    /**
     *发起提款订单
     */
    @TLogAspectExt(str = "发起提款订单", moduleName = "Withdraw", type = OperateTypeEnum.CREATE, convert = PlayerLogConvert.class)
    @PostMapping("/submitWithdrawOrder")
    public CommonResult submitWithdrawOrder(@RequestBody WithdrawOrderReq withdrawOrderReq) {
        log.info("开始提款：{}", JSONUtil.toJsonStr(withdrawOrderReq));
        try {
            StpPlayerUtil.checkDisable(StpPlayerUtil.getLoginId(), "finance");
        } catch (Exception exception) {
            log.error("用户禁止提款：{}", JSONUtil.toJsonStr(exception));
            throw exception(GlobalErrorCodeConstants.USER_IS_DISABLE);
        }
        return CommonResult.success(walletService.withdrew(withdrawOrderReq, StpPlayerUtil.getLoginIdAsLong()));
    }

    /**
     * 获取提款方式
     * @return
     */
    @TLogAspectExt(str = "获取提款方式", moduleName = "Withdraw", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getWithdrawMethod")
    public CommonResult<List<WithdrawTypeVO>> getWithdrawMethod(){
        List<WithdrawTypeVO> depositTypeVOList = dreamWithdrawTypeService.getWithdrawMethod(StpPlayerUtil.getLoginIdAsLong());
        return CommonResult.success(depositTypeVOList);
    }

}
