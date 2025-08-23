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
import org.springframework.scheduling.annotation.Scheduled;

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

    @Scheduled(fixedDelay = 2_000) // 每 10 秒跑一次
    @Transactional(rollbackFor = {Exception.class})
    public void getIssueNum() {
        String numberRecord = null; // 开奖码，如 "3,4,8"
        String sumRecord = null;    // 和值，如 "15"
        String winRecord = null;    // 中奖项集合（字符串，逗号分隔）
        String issue = null;        // 期号
        String sysIssueCode = null;
        String lotteryId = "1";

        // URL 从 Redis 读取；没有则写入默认（新）地址
        String url = (String) redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_SOURCE_URL + lotteryId);
        if (StringUtils.isNullOrEmpty(url)) {
            url = "https://cs00.vip/data/last/jnd28.json";
            redisTemplate.opsForValue().set(GameBetRedisKey.GAME_ISSUE_SOURCE_URL + lotteryId, url);
        }
        // 防缓存时间戳
        String reqUrl = url.contains("?") ? (url + "&_=" + System.currentTimeMillis()) : (url + "?_=" + System.currentTimeMillis());

        try {
            String jsonRet = HttpUtil.get(reqUrl, 5000);
            logger.info("getIssueNum  http result [{}]  >>> ", jsonRet);

            if (jsonRet != null && jsonRet.trim().length() > 0) {
                // 新返回就是一个对象，不再是 [ {...} ] 这种数组
                JSONObject obj = JSONObject.parseObject(jsonRet);

                // 期号：issue 或 qihao
                issue = obj.getString("issue");
                if (StringUtils.isNullOrEmpty(issue)) {
                    issue = obj.getString("qihao");
                }

                // 开奖码："code": "3,4,8"（优先）
                numberRecord = obj.getString("code");
                if (StringUtils.isNullOrEmpty(numberRecord)) {
                    // 兼容 "opennum": "3+4+8"
                    String openNum = obj.getString("opennum");
                    if (!StringUtils.isNullOrEmpty(openNum)) {
                        numberRecord = openNum.replace("+", ",");
                    }
                }

                // 和值
                Integer sum = obj.getInteger("sum");
                if (sum != null) {
                    sumRecord = String.valueOf(sum);
                }

                // 旧接口有 "ret"，新接口没有；这里构造一个最小可用的 winRecord：
                //   1) 至少包含和值（如 "15"），保证“和值X”的玩法能正常结算
                //   2) 极小 / 极大（你原代码追加的 32/33）继续兼容
                StringBuilder win = new StringBuilder();
                if (!StringUtils.isNullOrEmpty(sumRecord)) {
                    win.append(sumRecord); // 和值本身
                    try {
                        int s = Integer.parseInt(sumRecord);
                        if (s >= 0 && s <= 4) {       // 极小
                            if (win.length() > 0) win.append(",");
                            win.append("32");
                        } else if (s >= 22 && s <= 27) { // 极大
                            if (win.length() > 0) win.append(",");
                            win.append("33");
                        }
                    } catch (Exception ignore) { }
                }
                winRecord = win.toString();

                // 幂等：与 Redis 里已开奖期号不同才入库
                sysIssueCode = (String) redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_PRIZE + lotteryId);
                if (!StringUtils.isNullOrEmpty(issue) && (sysIssueCode == null || !issue.equals(sysIssueCode))) {
                    try {
                        // —— 期号从接口解析出来后，进入“新期号分支”里：update 前先兜底插入 —— //
                        String prizeKey = GameBetRedisKey.GAME_ISSUE_PRIZE + lotteryId;

                        // lastIssue 取 sysIssueCode；如果没有，尝试用 (issue-1)；再不行就回退成 issue 自身以满足 NOT NULL 约束
                        String lastIssue = (String) redisTemplate.opsForValue().get(prizeKey);
                        if (StringUtils.isNullOrEmpty(lastIssue)) {
                            try {
                                long cur = Long.parseLong(issue.replaceAll("[^0-9]", ""));
                                lastIssue = String.valueOf(cur - 1);
                            } catch (Exception ignore) {
                                lastIssue = issue; // 最后兜底，保证非空
                            }
                        }

                        // 计算三个销售时间：默认一期开3分钟（按需改）
                        //   start = now
                        //   end   = start + 150s (2分30秒)
                        //   draw  = start + 180s (3分钟)
                        java.util.Date saleStartTime = new java.util.Date();
                        java.util.Date saleEndTime   = new java.util.Date(saleStartTime.getTime() + 150_000L);
                        java.util.Date saleDrawTime  = new java.util.Date(saleStartTime.getTime() + 180_000L);

                        // 如果接口里带了官方开奖时间（你现在的新源有 "time"/"opentime"），可以用它覆盖 saleDrawTime：
                        // String openTimeStr = obj.getString("time"); if (StringUtils.isNullOrEmpty(openTimeStr)) openTimeStr = obj.getString("opentime");
                        // if (!StringUtils.isNullOrEmpty(openTimeStr)) {
                        //     try { saleDrawTime = java.sql.Timestamp.valueOf(openTimeStr.replace("T", " ").substring(0,19)); } catch (Exception ignore) {}
                        // }

                        long lotteryIdLong = 1L; // ← 替换为 jnd28 在你系统里的真实 lotteryId
                        int pre = gameIssueDao.insertIssueIfAbsent(
                                issue, lotteryIdLong, lastIssue, saleStartTime, saleEndTime, saleDrawTime
                        );
//                        logger.info("insertIssueIfAbsent issue={} lotteryId={} lastIssue={} start={} end={} draw={} rows={}",
//                                issue, lotteryIdLong, lastIssue, saleStartTime, saleEndTime, saleDrawTime, pre);

                        // 然后再执行开奖更新（你已经写好的逻辑）
                        int rows = gameIssueDao.drawGameIssue(numberRecord, sumRecord, winRecord, issue);
                        if (rows > 0) {
                            redisTemplate.opsForValue().set(prizeKey, issue);
//                            logger.info("saved issue={} (rows={}), code={}, sum={}, win={}",
//                                    issue, rows, numberRecord, sumRecord, winRecord);
                        } else {
                            logger.warn("DB update returned 0 rows for issue={}, check pending row(issuestatus=2) & lastIssueCode/time fields.", issue);
                        }

                    } catch (Exception e) {
                        logger.error("DB write failed for issue={}, skip redis update. err={}", issue, e.getMessage(), e);
                        throw e; // 回滚事务
                    }
                }

            }
        } catch (Exception e) {
            logger.error("getIssueNum [{}] for error[{}]  query url[{}]", issue, e.getMessage(), reqUrl, e);
            throw new BusinessException(ResponseCode.SERVER_ERROR);
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
            String issueCode = gim.getIssueCode();
            //		String nextIssueCode = gim.getIssueCode();
            Date saleEndTime = gim.getSaleEndTime();
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
            String issueCode = gim.getIssueCode();
            String winRecord = gim.getWinRecord();
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
            String issueCode = gim.getIssueCode();
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
