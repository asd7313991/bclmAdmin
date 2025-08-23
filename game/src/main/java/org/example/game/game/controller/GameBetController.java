package org.example.game.game.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.config.satoken.StpPlayerUtil;
import org.example.exception.BusinessException;
import org.example.game.game.constants.ResponseCode;
import org.example.game.game.enums.GameBetType;
import org.example.game.game.enums.GamePlanStop;
import org.example.game.game.enums.GamePlanType;
import org.example.game.game.service.GameOrderService;
import org.example.pojo.CommonResult;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/game")
public class GameBetController {


    @Resource
    GameOrderService gameOrderService;


    /**
     * 寻找当前issue
     */
    @RequestMapping(value = "/findcurIssue.do")
    @ResponseBody
//    @LimitLess
    // @Transactional
    public CommonResult findcurIssue(String lotteryId) {
        lotteryId = "1";
        return CommonResult.success(gameOrderService.findcurIssue(lotteryId));
    }

    /**
     * xiazhu
     *
     * @return
     */
    @RequestMapping(value = "/gameBet.do")
    @ResponseBody
    public CommonResult gameBet(String issueCode, String lotteryId, Long totalAmount, Integer numberRecord) {

        Assert.notNull(lotteryId, "彩种非法");

        Assert.notNull(issueCode, "期号非法");

        Assert.notNull(numberRecord, "投注内容非法");

        Double preWinNum = GameBetType.getRate(numberRecord);

        Assert.notNull(preWinNum, "投注内容非法");

//		if(StringUtils.isEmpty(gameName) || StringUtils.isEmpty(lotteryId) || StringUtils.isEmpty(issueCode)) {
//			return CommonResult.success(ResponseCode.PARAM_STR_NOT_EMPTY);
//		}

        if (totalAmount.intValue() < 1 || totalAmount.intValue() > 200000) {
            return CommonResult.error(ResponseCode.PARAM_TYPE_NUM, "参数必须为数字");
        }

        Integer userId = StpPlayerUtil.getLoginIdAsInt();
//		TEST
//		Integer userId = 120;

//		System.out.println("ss,startIsuueCode,userId" +issueCode+","+lotteryId+","+totalAmount+","+numberRecord+","+userId);
//

//		return CommonResult.success(ResponseCode.SUCCESS);

        try {

            gameOrderService.gameBet(userId, issueCode, lotteryId, totalAmount, numberRecord, preWinNum);
            return CommonResult.success("success");

        } catch (BusinessException e) {
            return CommonResult.error(e);
        }

    }

    @RequestMapping(value = "/gamePlan.do")
    @ResponseBody
    public CommonResult gamePlan(String lotteryId, Integer planType, Integer numberRecord, String startIsuueCode,
                                 Integer startMutiple, Integer totalIssue, Integer issueRato, Integer winRate, Integer stopMode,
                                 Integer startAmount) {

        Assert.notNull(lotteryId, "彩种非法");
        Assert.notNull(startAmount, "起始金额非法");
        Assert.notNull(totalIssue, "总期数非法");
        Assert.notNull(startIsuueCode, "期数非法");

        Double preWinNum = GameBetType.getRate(numberRecord);

        Assert.notNull(preWinNum, "投注内容非法");

        Assert.notNull(GamePlanStop.getName(stopMode), "追号停止模式非法");

        System.out.println("totalIssue............" + totalIssue);

        if (totalIssue > 8) {
            totalIssue = null;
            Assert.notNull(totalIssue, "总期数非法");
        }

//		Integer accPlanType = GamePlanType.getName(planType);

        GamePlanType gpt = GamePlanType.fromGamePlanType(planType);
        Assert.notNull(gpt, "追号类型非法");


        long totamount = 0;

        switch (gpt) {
            case DOUBLE:
                Assert.notNull(startMutiple, "开始倍数非法");
                totamount = startAmount * totalIssue * startMutiple;
                break;
            case PAY_OFF:
                Assert.notNull(startMutiple, "开始倍数非法");
                Assert.notNull(issueRato, "间隔期数非法");
                if (issueRato <= 0) {
                    issueRato = null;
                    Assert.notNull(issueRato, "间隔期数非法");
                }
                totamount = getMutNum(startAmount, startMutiple, totalIssue);
                break;
            case EARNINGS_RATE:
                Assert.notNull(winRate, "盈利率非法");
                totamount = startAmount * totalIssue;
                break;
            default:
                totamount = startAmount * totalIssue;
                break;
        }

        Integer userId = StpPlayerUtil.getLoginIdAsInt();

        try {

            gameOrderService.gamePlan(userId, lotteryId, planType, numberRecord, startIsuueCode, startMutiple,
                    totalIssue, issueRato, winRate, stopMode, startAmount, totamount, preWinNum);
            return CommonResult.success();

        } catch (BusinessException e) {
            return CommonResult.error(e);
        }

    }

    private int getMutNum(int startAmount, int startMutiple, int totalIssue) {
        int mutiple = 2;
        int tempMutiple = 1;
        int toamout = 0;
        for (int i = 0; i < totalIssue; i++) {
            toamout += startAmount * startMutiple * tempMutiple;
            tempMutiple = tempMutiple * mutiple;
        }
        return toamout;
    }


    /**
     * 寻找历史issue
     */
    @RequestMapping(value = "/findHistoryIssue.do")
    @ResponseBody
//    @LimitLess
    // @Transactional
    public CommonResult findHistoryIssue(String lotteryId, String issueCode) {
        lotteryId = "1";
        return CommonResult.success(gameOrderService.findHistoryIssue(lotteryId, issueCode));
    }

    /**
     * 寻找orderlist
     */
    @RequestMapping(value = "/getOrderById.do")
    @ResponseBody
    // @Transactional
    public CommonResult getOrderById(String orderCode) {
        String lotteryId = "1";
        Assert.notNull(orderCode, "订单编码为空");
        Integer userId = StpPlayerUtil.getLoginIdAsInt();
        Long id = Long.parseLong(orderCode);
        return CommonResult.success(gameOrderService.getOrderById(id, userId));
    }

    /**
     * 寻找order详情
     */
    @PostMapping(value = "/getOrderList.do")
    @ResponseBody
    // @Transactional
    public CommonResult getOrderList(@RequestBody Map<String, Object> data) {
        String lotteryId = data.get("lotteryId").toString();
        Integer status = Integer.valueOf(data.get("status").toString());
        Assert.notNull(status, "状态为空");
        Integer userId = StpPlayerUtil.getLoginIdAsInt();
        return CommonResult.success(gameOrderService.getOrderList(lotteryId, userId, status));
    }

    /**
     * 寻找order详情
     */
    @RequestMapping(value = "/findGamePlan.do")
    @ResponseBody
    // @Transactional
    public CommonResult findGamePlan(String lotteryId, Integer status) {
        lotteryId = "1";
        Assert.notNull(status, "状态为空");
        Integer userId = StpPlayerUtil.getLoginIdAsInt();
        return CommonResult.success(gameOrderService.findGamePlan(status, userId, null));
    }

    /**
     * 寻找order详情
     */
    @RequestMapping(value = "/findGamePlanDetail.do")
    @ResponseBody
    // @Transactional
    public CommonResult findGamePlanDetail(Long planId) {
        String lotteryId = "1";
        Assert.notNull(planId, "状态为空");
        Integer userId = StpPlayerUtil.getLoginIdAsInt();
        return CommonResult.success(gameOrderService.findGamePlanDetailByID(userId, planId));
    }


    /**
     * 统计
     */
    @RequestMapping(value = "/getOrderAnalysis.do")
    @ResponseBody
    // @Transactional
    public CommonResult getOrderAnalysis(String lotteryId) {
        lotteryId = "1";
        Integer userId = StpPlayerUtil.getLoginIdAsInt();
        return CommonResult.success(gameOrderService.getOrderAnalysis(lotteryId, userId));
    }

    /**
     * 生产期
     */
    @RequestMapping(value = "/buildIssue.do")
    @ResponseBody
    //  @LimitLess
    // @Transactional
    public CommonResult buildIssue(long issueDate, long issueNum) {

        Assert.notNull(issueDate, "状态为空");
        Assert.notNull(issueNum, "状态为空");

        gameOrderService.creatIssue(issueDate, issueNum);

        return CommonResult.success(ResponseCode.SUCCESS);
    }
}

