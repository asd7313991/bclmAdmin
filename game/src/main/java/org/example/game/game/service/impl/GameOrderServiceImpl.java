package org.example.game.game.service.impl;

import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import org.example.exception.BusinessException;
import org.example.game.game.constants.FinanceFlowType;
import org.example.game.game.constants.GameBetRedisKey;
import org.example.game.game.constants.ResponseCode;
import org.example.game.game.enums.GameBetType;
import org.example.game.game.enums.GamePlanStop;
import org.example.game.game.service.GameOrderService;
import org.example.game.game.util.SimpleDateFormatUtil;
import org.example.game.game.util.math.Arith;
import org.example.game.game.vo.GameBetItemReqVO;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.hf.HfGameIssueService;
import org.example.service.hf.HfGameOrderService;
import org.example.service.hf.HfGamePlanService;
import org.example.vo.hf.GameAnalysisVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.example.game.game.constants.CurrencyFlowType.*;


/**
 * Created by yhj on 17/6/27.
 */
@Service
public class GameOrderServiceImpl implements GameOrderService {

    private static Logger logger = LoggerFactory.getLogger(GameOrderServiceImpl.class);

    static List<Integer> settleStatus = Arrays.asList(
            CURRENCY_TO_GAME.getTypeDetail(),
            GAME_TO_CURRENCY.getTypeDetail(),
            GAME_UNFREEZE_CURRENCY.getTypeDetail());

    @Resource
    HfGameOrderService hfGameOrderService;
    @Resource
    HfGameIssueService hfGameIssueService;
    @Resource
    HfGamePlanService hfGamePlanService;
    @Resource
    private DreamUFinanceMainService mainDao;
    @Autowired
    private DreamUFinanceFlowService flowDao;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    DataSourceTransactionManager transactionManager;
    @Autowired
    private LockTemplate lockTemplate;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void gameBet(Integer userId, String issueCode, String lotteryId, Long totalAmount, Integer numberRecord, Double preWinNum) {

        Integer retcode = ResponseCode.SUCCESS;

        DreamUFinanceMainDO mainModel = mainDao.setectByUserId(Long.valueOf(userId));
        if (mainModel == null) {
            throw new BusinessException(ResponseCode.ERROR_CODE);
        }

        BigDecimal moneyUsable = mainModel.getMoneyUsable();

        HfGameIssueDO gim = findcurIssue(lotteryId);

        String redisIssue = gim.getIssueCode();

        long redisIssueInt = Long.parseLong(redisIssue);

        long issueCodeInt = Long.parseLong(issueCode);

        if (issueCodeInt < redisIssueInt) {
            throw new BusinessException(ResponseCode.BET_ISSUE_ERROR, "下注期号非法");
        }

        if (Arith.subtract(6, moneyUsable, totalAmount) < 0) {
            throw new BusinessException(ResponseCode.USER_MONEY_NOT_ENOUGH, "余额不足");
        }

        LockInfo lock = lockTemplate.lock("finance_money" + userId);
        if (lock == null) {
            throw new BusinessException(ResponseCode.LOCK_IS_BUSY, "业务繁忙中,请稍后再试");
        }

        logger.info("gamebet:userid:{},issueCode:{},totalAmount:{}", userId, issueCode, totalAmount);
        try {


            HfGameOrderDO om = new HfGameOrderDO();
            om.setIssuecode(issueCode);
            om.setLotteryid(lotteryId);
            om.setTotalamount(totalAmount);
            om.setNumberrecord(String.valueOf(numberRecord));
            om.setUserid(userId);
            om.setPrewinnum(preWinNum);
            hfGameOrderService.save(om);


            //修改mian表
            moneyUsable = moneyUsable.subtract(new BigDecimal(totalAmount));
            mainModel.setMoneyUsable(moneyUsable);
            mainDao.update(mainModel);

            DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
            flowModel.setUserId(Long.valueOf(userId));
            flowModel.setType(FinanceFlowType.HF_BUY_GOODS.getType());
            flowModel.setTypeDetail(FinanceFlowType.HF_BUY_GOODS.getTypeDetail());
            flowModel.setRemark(FinanceFlowType.HF_BUY_GOODS.getRemark());
            flowModel.setMoney(BigDecimal.valueOf(totalAmount));
            flowModel.setCOrderId(userId + "-buy-" + issueCode);
            flowModel.setMoneyLeft(moneyUsable);
            flowDao.save(flowModel);


        } catch (Exception e) {
            logger.error("购买失败：用户id:{},issueCode：{}，原因：{}", userId, issueCode, e.getStackTrace());
            throw new BusinessException(ResponseCode.ERROR_CODE);
        } finally {
//            if (lock != null) {
//                lock.unlock();
//            }
            //释放锁
            lockTemplate.releaseLock(lock);
        }

//      return retcode;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void gameBet(Integer userId,
                        String issueCode,
                        String lotteryId,
                        Long totalAmount,                         // 可为 null（则以后端合计为准）
                        List<GameBetItemReqVO> items) {

        // ===== 1) 用户与余额 =====
        DreamUFinanceMainDO mainModel = mainDao.setectByUserId(Long.valueOf(userId));
        if (mainModel == null) {
            throw new BusinessException(ResponseCode.ERROR_CODE, "用户不存在");
        }
        BigDecimal moneyUsable = mainModel.getMoneyUsable();
        BigDecimal cost = BigDecimal.valueOf(totalAmount);
        if (moneyUsable.compareTo(cost) < 0) {
            throw new BusinessException(ResponseCode.USER_MONEY_NOT_ENOUGH, "余额不足");
        }

        // ===== 2) 期号合法性 =====
        HfGameIssueDO gim = findcurIssue(lotteryId);
        String redisIssue = gim.getIssueCode(); // 注意：你的 DO 字段是 issuecode，小心 getter 名
        long redisIssueInt = Long.parseLong(redisIssue);
        long issueCodeInt  = Long.parseLong(issueCode);
        if (issueCodeInt < redisIssueInt) {
            throw new BusinessException(ResponseCode.BET_ISSUE_ERROR, "下注期号非法");
        }

        // ===== 3) 加分布式锁（资金并发安全） =====
        LockInfo lock = lockTemplate.lock("finance_money:" + userId);
        if (lock == null) {
            throw new BusinessException(ResponseCode.LOCK_IS_BUSY, "业务繁忙中,请稍后再试");
        }

        logger.info("gameBet start: userId={}, issueCode={}, lotteryId={}, totalAmount={}, items={}",
                userId, issueCode, lotteryId, totalAmount, items.size());

        try {
            // ===== 4) 生成订单（多条） =====
            List<HfGameOrderDO> toSave = new java.util.ArrayList<>(items.size());
            for (GameBetItemReqVO it : items) {
                Integer nr = it.getNumberRecord();
                Long    amt = it.getTotalAmount();

                // 计算每条注项的赔率/预期（如果你的工具类只支持单个号码）
                Double preWinNum = GameBetType.getRate(nr);  // ← 如果你已有 getRate(List<Integer>)，也可写在上层

                HfGameOrderDO om = new HfGameOrderDO();
                om.setIssuecode(issueCode);
                om.setLotteryid(lotteryId);
                om.setTotalamount(amt);
                om.setNumberrecord(String.valueOf(nr));
                om.setUserid(userId);
                om.setPrewinnum(preWinNum);

                toSave.add(om);
            }
            // 批量落库（如果没有批量方法，就循环 save）
            hfGameOrderService.saveBatch(toSave);

            // ===== 5) 扣减余额 =====
            moneyUsable = moneyUsable.subtract(cost);
            mainModel.setMoneyUsable(moneyUsable);
            mainDao.update(mainModel);

            // ===== 6) 资金流水（记一条总额流水） =====
            DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
            flowModel.setUserId(Long.valueOf(userId));
            flowModel.setType(FinanceFlowType.HF_BUY_GOODS.getType());
            flowModel.setTypeDetail(FinanceFlowType.HF_BUY_GOODS.getTypeDetail());
            flowModel.setRemark(FinanceFlowType.HF_BUY_GOODS.getRemark());
            flowModel.setMoney(cost);
            flowModel.setCOrderId(userId + "-buy-" + issueCode);  // 订单组号，可按需换为真正的订单号
            flowModel.setMoneyLeft(moneyUsable);
            flowDao.save(flowModel);

            logger.info("gameBet success: userId={}, issueCode={}, totalAmount={}, moneyLeft={}",
                    userId, issueCode, totalAmount, moneyUsable);

        } catch (Exception e) {
            logger.error("gameBet failed: userId={}, issueCode={}, err={}", userId, issueCode, e.getMessage(), e);
            throw new BusinessException(ResponseCode.ERROR_CODE, "购买失败");
        } finally {
            lockTemplate.releaseLock(lock);
        }
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void gamePlan(Integer userId, String lotteryId, Integer planType, Integer numberRecord, String startIsuueCode, Integer startMutiple, Integer totalIssue, Integer issueRato, Integer winRate, Integer stopMode, Integer startAmount, long totamount, Double preWinNum) {

//        DistributeLock lock = null;

        DreamUFinanceMainDO mainModel = mainDao.setectByUserId(Long.valueOf(userId));
        if (mainModel == null) {
            throw new BusinessException(ResponseCode.ERROR_CODE);
        }

        BigDecimal moneyUsable = mainModel.getMoneyUsable();

        HfGameIssueDO gim = findcurIssue(lotteryId);

        if (gim == null) {
            throw new BusinessException(ResponseCode.BET_ISSUE_ERROR, "当前期号非法");
        }

        String redisIssue = gim.getIssueCode();


        long redisIssueInt = Long.parseLong(redisIssue);
        long issueCodeInt = Long.parseLong(startIsuueCode);

        if (issueCodeInt < redisIssueInt) {
            throw new BusinessException(ResponseCode.BET_ISSUE_ERROR, "下注期号非法");
        }

        if (Arith.subtract(6, moneyUsable, totamount) < 0) {
            throw new BusinessException(ResponseCode.USER_MONEY_NOT_ENOUGH, "余额不足");
        }
        LockInfo lock = lockTemplate.lock("finance_money" + userId);

        if (lock == null) {
            throw new BusinessException(ResponseCode.LOCK_IS_BUSY, "业务繁忙中,请稍后再试");
        }

        try {
//            lock = lockFactory.newLock("finance_money" + userId);
//            lock.lock();

            HfGamePlanDO om = new HfGamePlanDO();
            om.setLotteryid(lotteryId);
            om.setPlantype(planType);
            om.setTotalissue(totalIssue);
            om.setStartisuuecode(startIsuueCode);
            om.setStartmutiple(startMutiple);
            om.setIssuerate(issueRato);
            om.setWinrate(winRate);
            om.setStopmode(GamePlanStop.getName(stopMode));
            om.setUserid(userId);
            om.setNumberrecord(String.valueOf(numberRecord));
            om.setStartamount(startAmount);
            om.setTotamount(BigDecimal.valueOf(totamount));
            om.setPreWinNum(preWinNum);

            hfGamePlanService.save(om);


            //修改mian表  TODO 这里有问题
            BigDecimal moneyFrozen = mainModel.getMoneyFrozen();

            //可用资金余额
            moneyUsable = mainModel.getMoneyUsable().subtract(new BigDecimal(totamount));
            //冻结资金
            moneyFrozen = mainModel.getMoneyFrozen().add(new BigDecimal(totamount));
            mainModel.setMoneyUsable(moneyUsable);
            mainModel.setMoneyFrozen(moneyFrozen);
            mainDao.update(mainModel);

            DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
            flowModel.setUserId(Long.valueOf(userId));
            flowModel.setType(FinanceFlowType.HF_FROZEN_GOODS.getType());
            flowModel.setTypeDetail(FinanceFlowType.HF_FROZEN_GOODS.getTypeDetail());
            flowModel.setRemark(FinanceFlowType.HF_FROZEN_GOODS.getRemark());
            flowModel.setMoney(new BigDecimal(totamount));
            flowModel.setCOrderId(userId + "-buyplan-" + startIsuueCode);
            flowModel.setMoneyLeft(moneyUsable);
            flowDao.save(flowModel);


        } catch (Exception e) {
            logger.info("购买追号失败：用户id:{},issueCode：{}，原因：{}", userId, startIsuueCode, e.getStackTrace());
            throw new BusinessException(ResponseCode.ERROR_CODE);
        } finally {
//            if (lock != null) {
//                lock.unlock();
//            }

            lockTemplate.releaseLock(lock);
        }

        logger.info("settle gameplan[{}] for user[{}]  buy >>> ending", startIsuueCode, userId);
    }

    @Override
    public HfGameIssueDO findcurIssue(String lotteryId) {

//        HfGameIssueDO issueModel = (HfGameIssueDO) redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_BEING + lotteryId);
        HfGameIssueDO issueModel = hfGameIssueService.findcurIssue(lotteryId);
//        if (null == issueModel) {
//            issueModel = hfGameIssueService.findcurIssue(lotteryId);
//            redisTemplate.opsForValue().set(GameBetRedisKey.GAME_ISSUE_BEING + lotteryId, issueModel);
//        }

        return issueModel;
    }

    @Override
    public List<HfGameIssueDO> findHistoryIssue(String lotteryId, String issueCode) {

//    	List<GameIssueModel> issueModel = (List<GameIssueModel>)redisTemplate.opsForValue().get(GameBetRedisKey.GAME_ISSUE_HISTORY+"_"+lotteryId);

//    	if(null == issueModel) {
//    		issueModel = hfGameIssueService.findHistoryIssue(lotteryId);
//    		redisTemplate.opsForValue().set(GameBetRedisKey.GAME_ISSUE_HISTORY+lotteryId, issueModel);
//    	}

        return hfGameIssueService.findHistoryIssue(lotteryId, issueCode);
    }

    @Override
    public List<HfGameOrderDO> getOrderList(String lotteryId, Integer userId, Integer status) {
        return hfGameOrderService.getOrderList(lotteryId, userId, status);
    }

    @Override
    public HfGameOrderDO getOrderById(Long id, Integer userId) {
        return hfGameOrderService.getOrderById(id, userId);
    }

    @Override
    public List<GameAnalysisVo> getOrderAnalysis(String lotteryId, Integer userId) {
        return hfGameOrderService.getOrderAnalysis(lotteryId, userId);
    }

    @Override
    public void creatIssue(long issueDate, long issueNum) {
        String lotteryId = "1";
        long ttSale = 210000;
        long saleTime = 180000;
//		String startSale = issueTempDate;
//		String startSale = "2023-09-14 10:15:00";

        String startSale = SimpleDateFormatUtil.dateToString(new Date(issueDate));
//		startSale = SimpleDateFormatUtil.dateByNum(startSale, -ttSale);
//		startSale = String.valueOf(issueDate);

        String endTime = "19:00:00";
        String startTime = "20:00:00";

        String todayDate = SimpleDateFormatUtil.dateByNow();

        String tommoDate = SimpleDateFormatUtil.dateByTommo();


        String endTT = null;

        String startTT = todayDate + " " + endTime;
        //TODO stop sale one two hours


        if (SimpleDateFormatUtil.dateCompare(startTT, startSale)) {
            endTT = tommoDate + " " + endTime;
        } else {
            endTT = todayDate + " " + endTime;
        }

        long startNum = issueNum;
        if (null != endTT) {
            while (SimpleDateFormatUtil.dateCompare(startSale, endTT)) {
                String stopSaleDate = SimpleDateFormatUtil.dateByNum(startSale, saleTime);
                String stopTime = SimpleDateFormatUtil.dateByNum(startSale, ttSale);
//				issueNum++;
                HfGameIssueDO gim = new HfGameIssueDO();
                gim.setLastIssueCode(String.valueOf(startNum - 1));
                gim.setIssueCode(String.valueOf(startNum));
                gim.setLotteryid(lotteryId);

                gim.setSaleStartTime(SimpleDateFormatUtil.stringToDate(startSale));
                gim.setSaleEndTime(SimpleDateFormatUtil.stringToDate(stopSaleDate));
                gim.setSaleDrawTime(SimpleDateFormatUtil.stringToDate(stopTime));

                hfGameIssueService.save(gim);


//				System.out.println(issueNum + "..." + startSale + "..." + stopSaleDate + "..." + stopTime);
                startSale = stopTime;
                startNum++;
            }
        }

    }

    public List<HfGamePlanDO> findGamePlan(Integer status, Integer userId, Integer priStatus) {
        return hfGamePlanService.findGamePlan(status, userId, priStatus, null);
    }

    public List<HfGamePlanDetailDO> findGamePlanDetailByID(Integer userId, Long planId) {
        return hfGamePlanService.findGamePlanDetailByID(userId, planId);
    }

    @Override
    public void updateGameOrder(String lotteryId, String[] winRecords, String issueCode) {
        hfGameOrderService.updateGameOrder(lotteryId,winRecords,issueCode);
    }

    @Override
    public void updateGameOrderByunprize(String lotteryId, String[] winRecords, String issueCode) {
        hfGameOrderService.updateGameOrderByunprize(lotteryId,winRecords,issueCode);
    }

    @Override
    public List<Map<String, Object>> selectGamePlanByPrize(String lotteryId, String[] winRecords, String issueCode) {
        return hfGameOrderService.selectGamePlanByPrize(lotteryId,winRecords,issueCode);
    }

    @Override
    public List<HfGameOrderDO> getSendIssue(String lotteryId, String issueCode, int value) {
        return hfGameOrderService.getSendIssue(lotteryId,issueCode,value);
    }




}
