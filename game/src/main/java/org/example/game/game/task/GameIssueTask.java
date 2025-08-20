package org.example.game.game.task;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import com.mysql.cj.util.StringUtils;
import org.example.exception.BusinessException;
import org.example.game.game.constants.FinanceFlowType;
import org.example.game.game.constants.GameBetRedisKey;
import org.example.game.game.constants.ResponseCode;
import org.example.game.game.enums.*;
import org.example.game.game.service.GameOrderService;
import org.example.game.game.util.math.Arith;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.hf.HfGameIssueService;
import org.example.service.hf.HfGamePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wms on 2017/9/28.
 */
@Component
public class GameIssueTask {
    @Autowired
    HfGameIssueService gameIssueDao;
    @Autowired
    GameOrderService gameOrderDao;
    @Autowired
    HfGamePlanService gamePlanDao;

    @Autowired
    private DreamUFinanceMainService mainDao;
    @Autowired
    private DreamUFinanceFlowService flowDao;
    @Autowired
    GameOrderService gameOrderService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private LockTemplate lockTemplate;

    private static Logger logger = LoggerFactory.getLogger(GameIssueTask.class);

    @Transactional(rollbackFor = {Exception.class})
    public void getIssueNum() {
        String numberRecord = null;
        String sumRecord = null;
        String winRecord = null;
        String issue = null;
        String sysIssueCode = null;
        String lotteryId = "1";

        String url = (String) redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_SOURCE_URL + lotteryId);
        if (StringUtils.isNullOrEmpty(url)) {
            url = "http://8.211.36.227:7099/pc28";
            redisTemplate.opsForValue().set(GameBetRedisKey.GAME_ISSUE_SOURCE_URL + lotteryId, url);
        }

        try {
            String jsonRet = HttpUtil.get(url, 5000);

            logger.info("getIssueNum  http result [{}]  >>> ", jsonRet);

            if (null != jsonRet) {
                String realJson = jsonRet.replaceAll("\\[", "").replaceAll("\\]", "");
                JSONObject retJson = JSONObject.parseObject(realJson);
                numberRecord = retJson.getString("result");
                sumRecord = retJson.getString("sumResult");
                winRecord = retJson.getString("ret");
                issue = retJson.getString("number");
                Integer bigOrSmalSide = Integer.parseInt(sumRecord);
                if (bigOrSmalSide > -1 && bigOrSmalSide < 5) {
                    if (winRecord.indexOf("32") == -1) {
                        winRecord = winRecord + "32";
                    }
                } else if (bigOrSmalSide > 21 && bigOrSmalSide < 28) {
                    if (winRecord.indexOf("33") == -1) {
                        winRecord = winRecord + "33";
                    }
                }
                if (winRecord.endsWith(",")) {
                    winRecord = winRecord.substring(0, winRecord.length() - 1);
                }
//				GameIssueModel gim = gameOrderService.findcurIssue(lotteryId);
                sysIssueCode = (String) redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_PRIZE + lotteryId);
                if (sysIssueCode == null || !issue.equals(sysIssueCode)) {
                    gameIssueDao.drawGameIssue(numberRecord, sumRecord, winRecord, issue);
                    redisTemplate.opsForValue().set(GameBetRedisKey.GAME_ISSUE_PRIZE + lotteryId, issue);
                    logger.info("getIssueNum [{}] for issue[{}] >>> ending", issue,
                            numberRecord + "-" + sumRecord + "-" + winRecord);
                }

            }

        } catch (Exception e) {
            logger.error("getIssueNum [{}] for error[{}]  query >>> ending", sysIssueCode, e.getStackTrace());
        }

    }

    /**
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    public void issueOpenClose() {
        String lotteryId = "1";
        HfGameIssueDO gim = gameOrderService.findcurIssue(lotteryId);
        if (null != gim) {
            String issueCode = gim.getIssuecode();
            //		String nextIssueCode = gim.getIssueCode();
            Date saleEndTime = gim.getSaleendtime();
            String nextIssueCode = String.valueOf(Long.parseLong(issueCode) + 1);
            long saleEnd = saleEndTime.getTime();
            long nowTime = System.currentTimeMillis();
            logger.info("task start issueCode[{}] for nextIssueCode[{}]  buy >>> ending", issueCode, nextIssueCode);
            if (nowTime > saleEnd) {
                gameIssueDao.issueOpenClose(GameIssueStatus.SALE_END.getValue(), lotteryId, issueCode);
                gameIssueDao.issueOpenClose(GameIssueStatus.SALE_START.getValue(), lotteryId, nextIssueCode);
                redisTemplate.delete(GameBetRedisKey.GAME_ISSUE_BEING + lotteryId);
                // TODO redis current issue
            }
        }

    }

    /**
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    public void calIssueByNum() {
        String lotteryId = "1";
        HfGameIssueDO gim = gameIssueDao.findIssueByStatus(lotteryId, GameIssueStatus.ACK_DRAW_RESULT.getValue());
        Object lockredis = redisTemplate.opsForValue().get(GameBetRedisKey.CAL_ISSUE_LOCK);
        if (null != gim && null == lockredis) {
            redisTemplate.opsForValue().set(GameBetRedisKey.CAL_ISSUE_LOCK, 1);
            String issueCode = gim.getIssuecode();
            String winRecord = gim.getWinrecord();
            String[] winRecords = winRecord.split("\\,");
            gameOrderDao.updateGameOrder(lotteryId, winRecords, issueCode);
            gameOrderDao.updateGameOrderByunprize(lotteryId, winRecords, issueCode);
            gameIssueDao.issueOpenClose(GameIssueStatus.CALCULATION.getValue(), lotteryId, issueCode);

            String ids = null;
            //TODO 追号停止t1

            LockInfo lock = null;
            List<Map<String, Object>> gameOrderPlanDoList = gameOrderDao.selectGamePlanByPrize(lotteryId,
                    winRecords, issueCode);
            if (null != gameOrderPlanDoList || !gameOrderPlanDoList.isEmpty()) {
                for (Map<String, Object> gpdm : gameOrderPlanDoList) {

                    int planType = Integer.parseInt(String.valueOf(gpdm.get("planType")));
                    Double accwin = Double.parseDouble(String.valueOf(gpdm.get("accWin")));
                    int planId = Integer.parseInt(String.valueOf(gpdm.get("gamePlanId")));
                    int soldAmount = Integer.parseInt(String.valueOf(gpdm.get("soldAmount")));
                    int stopMode = Integer.parseInt(String.valueOf(gpdm.get("stopMode")));
                    int winRate = Integer.parseInt(String.valueOf(gpdm.get("winRate")));
                    Double winAmount = Double.parseDouble(String.valueOf(gpdm.get("winAmount")));
                    int userId = Integer.parseInt(String.valueOf(gpdm.get("userId")));
                    int totamount = Integer.parseInt(String.valueOf(gpdm.get("totalAmount")));

                    logger.info("calIssueByNum gameOrderPlanDoList [{}]  >>> ", gpdm);

                    GamePlanType gpt = GamePlanType.fromGamePlanType(planType);
                    double win = Arith.add(winAmount, accwin);
                    boolean planflag = false;
                    switch (gpt) {
                        case EARNINGS_RATE:
                            //盈利追号

                            double rate = Arith.divide(winRate, 100, 2);
                            double sale = Arith.add(Arith.multiply(Double.parseDouble(String.valueOf(soldAmount)), rate, 2), Double.parseDouble(String.valueOf(soldAmount)));
                            if (win > sale)
                                planflag = true;
                            break;
                        default:
                            //普通追号
                            if (GamePlanStop.WIN_STOP.getValue() == stopMode) {
                                planflag = true;
                            }
                            break;
                    }
                    if (planflag) {
                        logger.info("calIssueByNum planflag [{}]  >>> ", planflag);
                        if (null == ids) {
                            ids = String.valueOf(planId);
                        } else {
                            ids = ids + "," + planId;
                        }
                        try {

                            lock = lockTemplate.lock("finance_money" + userId);

                            DreamUFinanceMainDO mainModel = mainDao.findByUserId(userId);
                            if (mainModel == null) {
                                throw new BusinessException(ResponseCode.ERROR_CODE);
                            }
                            Double moneyFrozen = mainModel.getMoneyFrozen().doubleValue();
                            Double moneyUsable = mainModel.getMoneyUsable().doubleValue();
                            // 修改mian表
                            Double getFrozenAmount = Arith.subtract(totamount, soldAmount);

                            moneyFrozen = Arith.subtract(moneyFrozen, getFrozenAmount);
                            moneyUsable = Arith.add(moneyUsable, getFrozenAmount);

                            logger.info("calIssueByNum getFrozenAmount [{}] planId  [{}]  >>> ", getFrozenAmount, planId);

                            if (getFrozenAmount > 0) {
                                mainModel.setMoneyFrozen(new BigDecimal(moneyFrozen));
                                mainModel.setMoneyUsable(new BigDecimal(moneyUsable));

                                DreamUFinanceFlowDO flowModel1 = new DreamUFinanceFlowDO();
                                flowModel1.setUserId(Long.valueOf(userId));
                                flowModel1.setType(FinanceFlowType.HF_UNFROZEN_RUTRUN_GOODS.getType());
                                flowModel1.setTypeDetail(FinanceFlowType.HF_UNFROZEN_RUTRUN_GOODS.getTypeDetail());
                                flowModel1.setRemark(FinanceFlowType.HF_UNFROZEN_RUTRUN_GOODS.getRemark());
                                flowModel1.setMoney(BigDecimal.valueOf(getFrozenAmount));
                                flowModel1.setCOrderId(userId + "-returnplanId-" + planId);
                                flowModel1.setMoneyLeft(BigDecimal.valueOf(moneyUsable));
                                flowDao.save(flowModel1);
                            } else {
                                mainModel.setMoneyFrozen(BigDecimal.valueOf(moneyFrozen));
                            }

                            mainDao.update(mainModel);


                            DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
                            flowModel.setUserId(Long.valueOf(userId));
                            flowModel.setType(FinanceFlowType.HF_UNFROZEN_GOODS.getType());
                            flowModel.setTypeDetail(FinanceFlowType.HF_UNFROZEN_GOODS.getTypeDetail());
                            flowModel.setRemark(FinanceFlowType.HF_UNFROZEN_GOODS.getRemark());
                            flowModel.setMoney(BigDecimal.valueOf(getFrozenAmount));
                            flowModel.setCOrderId(userId + "-stopplanId-" + planId);
                            flowModel.setMoneyLeft(BigDecimal.valueOf(moneyUsable));
                            flowDao.save(flowModel);

                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new BusinessException(ResponseCode.ERROR_CODE, e.getMessage());
                        } finally {
                            if (lock != null) {
                                //lock.unlock();
                                lockTemplate.releaseLock(lock);
                                redisTemplate.delete(GameBetRedisKey.CAL_ISSUE_LOCK);
                            }
                        }

                    }

                    //增加中奖金额
                    logger.info("calIssueByNum win [{}] planId  [{}]  >>> ", accwin, planId);
                    gamePlanDao.updateGamePlan(null, accwin, null, null, planId);
                }
            }
            //停止计划
            if (null != ids) {
                String[] idslist = ids.split("\\,");
                gamePlanDao.updateGamePlanByStop(idslist, GamePlanStatus.STOP.getValue());
                gamePlanDao.updateGamePlanDetailByPlan(idslist, GamePlanDetailStatus.CANCEL.getValue());
            }

            redisTemplate.delete(GameBetRedisKey.CAL_ISSUE_LOCK);

        }//end if
    }

    /**
     *
     */
    @Transactional(rollbackFor = {Exception.class})
    public void sendIssueByNum() {//TODO 当期单用户单订单中奖限制500w plan 更新winamount
        String lotteryId = "1";
        HfGameIssueDO gim = gameIssueDao.findIssueByStatus(lotteryId, GameIssueStatus.CALCULATION.getValue());
        Object lockredis = redisTemplate.opsForValue().get(GameBetRedisKey.SEND_ISSUE_LOCK);
        if (null != gim && null == lockredis) {
            redisTemplate.opsForValue().set(GameBetRedisKey.SEND_ISSUE_LOCK, 1);
            String issueCode = gim.getIssuecode();
            List<HfGameOrderDO> prizeOrderList = gameOrderDao.getSendIssue(lotteryId, issueCode,
                    GameOrderStatus.PRIZE.getValue());
            if (prizeOrderList != null) {
                LockInfo lock = null;

                for (HfGameOrderDO gmp : prizeOrderList) {
                    try {
                        Integer userId = gmp.getUserid();
                        Double totalAmount = gmp.getAccwin();
                        if (5000000 < totalAmount) totalAmount = 5000000.0;
                        Long orderid = gmp.getId().longValue();
                        Long planId = gmp.getGameplanid().longValue();
                        lock = lockTemplate.lock("finance_money" + userId);

                        DreamUFinanceMainDO mainModel = mainDao.findByUserId(userId);
                        if (mainModel == null) {
                            throw new BusinessException(ResponseCode.ERROR_CODE);
                        }
                        Double moneyUsable = mainModel.getMoneyUsable().doubleValue();
                        // 修改mian表
                        moneyUsable = Arith.add(moneyUsable, totalAmount);
                        mainModel.setMoneyUsable(BigDecimal.valueOf(moneyUsable));

                        mainDao.update(mainModel);
                        DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
                        flowModel.setUserId(userId.longValue());
                        flowModel.setType(FinanceFlowType.HF_PRZIE_GOODS.getType());
                        flowModel.setTypeDetail(FinanceFlowType.HF_PRZIE_GOODS.getTypeDetail());
                        flowModel.setRemark(FinanceFlowType.HF_PRZIE_GOODS.getRemark());
                        flowModel.setMoney(BigDecimal.valueOf(totalAmount));
                        flowModel.setCOrderId(userId + "-prize-" + orderid);
                        flowModel.setMoneyLeft(BigDecimal.valueOf(moneyUsable));
                        flowDao.save(flowModel);


                    } catch (Exception e) {
                        throw new BusinessException(ResponseCode.ERROR_CODE, "派奖错误");
                    } finally {
                        if (lock != null) {
                            lockTemplate.releaseLock(lock);
                            redisTemplate.delete(GameBetRedisKey.SEND_ISSUE_LOCK);
                        }
                    }
                }
                gameIssueDao.issueOpenClose(GameIssueStatus.ISSUE_OVER.getValue(), lotteryId, issueCode);
            }
            redisTemplate.delete(GameBetRedisKey.SEND_ISSUE_LOCK);
        }//end if

        // batch update mainio flow

//    	gameIssueDao.issueOpenClose(GameIssueStatus.ISSUE_OVER.getValue(), lotteryId, issueCode);
    }

}
