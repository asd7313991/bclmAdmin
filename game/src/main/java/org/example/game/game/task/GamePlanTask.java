package org.example.game.game.task;

import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import org.example.distributelock.DistributeLock;
import org.example.distributelock.DistributeLockFactory;
import org.example.exception.BusinessException;
import org.example.game.game.constants.FinanceFlowType;
import org.example.game.game.constants.GameBetRedisKey;
import org.example.game.game.constants.ResponseCode;
import org.example.game.game.enums.GamePlanDetailStatus;
import org.example.game.game.enums.GamePlanStatus;
import org.example.game.game.enums.GamePlanType;
import org.example.game.game.service.GameOrderService;
import org.example.game.game.util.math.Arith;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.service.dream.DreamUFinanceFlowService;
import org.example.service.dream.DreamUFinanceMainService;
import org.example.service.hf.HfGameIssueService;
import org.example.service.hf.HfGamePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wms on 2017/9/28.
 */

@EnableScheduling
@Component
public class GamePlanTask {
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
    RedisTemplate redisTemplate;

    @Autowired
    GameOrderService gameOrderService;

    @Autowired
    private LockTemplate lockTemplate;


    private static Logger logger = LoggerFactory.getLogger(GamePlanTask.class);

    @Transactional(rollbackFor = {Exception.class})
    @Scheduled(fixedRate = 3000, initialDelay = 1000)
    @Async
    public void buildPlanDetail() {
        List<HfGamePlanDO> gamePlanModelList = gamePlanDao.findGamePlanByStatus(GamePlanStatus.UN_EXEC.getValue());
        Object lockredis = redisTemplate.opsForValue().get(GameBetRedisKey.BUILD_PLAN_LOCK);
        if (gamePlanModelList != null && null == lockredis) {
            // lock
            redisTemplate.opsForValue().set(GameBetRedisKey.BUILD_PLAN_LOCK, 1);
            for (HfGamePlanDO gamePlanModel : gamePlanModelList) {
                int planType = gamePlanModel.getPlantype();
                String startIsuueCode = gamePlanModel.getStartisuuecode();
                int totalIssue = gamePlanModel.getTotalissue();
                int startAmount = gamePlanModel.getStartamount();
                int startMutiple = gamePlanModel.getStartmutiple();
                long id = gamePlanModel.getId();
                int gameIssue = Integer.parseInt(startIsuueCode);

                try {
                    gamePlanDao.updateGamePlan(null, null, null, GamePlanStatus.EXECUTABLE.getValue(), id);

                    GamePlanType gpt = GamePlanType.fromGamePlanType(planType);

                    switch (gpt) {
                        case DOUBLE:
                            // 同倍追号
                            for (int i = 0; i < totalIssue; i++) {
                                HfGamePlanDetailDO gpd = new HfGamePlanDetailDO();
                                gpd.setGameissue(String.valueOf(gameIssue));
                                gpd.setMutiple(startMutiple);
                                gpd.setTotamount(startAmount * startMutiple);
                                gpd.setPlancode(String.valueOf(id));
                                gpd.setDetailstatus(GamePlanDetailStatus.UN_EXEC.getValue());
                                gamePlanDao.createGamePlanDetail(gpd);
                                gameIssue++;
                            }
                            break;
                        case PAY_OFF:
                            // 翻倍追号
                            int mutipe = 2;
                            int tempMutiple = 1;
                            int issueRate = gamePlanModel.getIssuerate();
                            for (int i = 0; i < totalIssue; i++) {
                                int calMuti = startMutiple * tempMutiple;
                                HfGamePlanDetailDO gpd = new HfGamePlanDetailDO();
                                gpd.setGameissue(String.valueOf(gameIssue));
                                gpd.setMutiple(calMuti);
                                gpd.setTotamount(startAmount * calMuti);
                                gpd.setPlancode(String.valueOf(id));
                                gpd.setDetailstatus(GamePlanDetailStatus.UN_EXEC.getValue());
                                gamePlanDao.createGamePlanDetail(gpd);
                                tempMutiple = tempMutiple * mutipe;
                                gameIssue += issueRate;
                            }
                            break;
                        case EARNINGS_RATE:
                            // 盈利追号
                            for (int i = 0; i < totalIssue; i++) {
                                HfGamePlanDetailDO gpd = new HfGamePlanDetailDO();
                                gpd.setGameissue(String.valueOf(gameIssue));
                                gpd.setMutiple(startMutiple);
                                gpd.setTotamount(startAmount);
                                gpd.setPlancode(String.valueOf(id));
                                gpd.setDetailstatus(GamePlanDetailStatus.UN_EXEC.getValue());
                                gamePlanDao.createGamePlanDetail(gpd);
                                gameIssue++;
                            }
                            break;
                        default:
                            // 普通追号
                            for (int i = 0; i < totalIssue; i++) {
                                HfGamePlanDetailDO gpd = new HfGamePlanDetailDO();
                                gpd.setGameissue(String.valueOf(gameIssue));
                                gpd.setMutiple(startMutiple);
                                gpd.setTotamount(startAmount);
                                gpd.setPlancode(String.valueOf(id));
                                gpd.setDetailstatus(GamePlanDetailStatus.UN_EXEC.getValue());
                                gamePlanDao.createGamePlanDetail(gpd);
                                gameIssue++;
                            }
                            break;
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    throw new BusinessException(ResponseCode.ERROR_CODE, "生成计划详情操作错误");
                } finally {

                    redisTemplate.delete(GameBetRedisKey.BUILD_PLAN_LOCK);
                }

            } // end for

            redisTemplate.delete(GameBetRedisKey.BUILD_PLAN_LOCK);

        }

    }

    /**
     *
     */


    @Transactional(rollbackFor = {Exception.class})
    @Scheduled(fixedRate = 5000, initialDelay = 2000)
    public void buildOrderByPlan() {
        String lotteryId = "1";
        HfGameIssueDO gim = gameOrderService.findcurIssue(lotteryId);
        Object lockredis = redisTemplate.opsForValue().get(GameBetRedisKey.BUILD_PLAN_ORDER_LOCK);
        if (null != gim && null == lockredis) {
            redisTemplate.opsForValue().set(GameBetRedisKey.BUILD_PLAN_ORDER_LOCK, 1);
            String issueCode = gim.getIssuecode();
            String lastIssueCode = gim.getLastissuecode();
            List<HfGamePlanDetailDO> gamePlanDetailModelList = gamePlanDao.findGamePlanDetail(lotteryId,
                    String.valueOf(issueCode), GamePlanDetailStatus.UN_EXEC.getValue(), lastIssueCode);

            if (null != gamePlanDetailModelList) {


                LockInfo lock = null;
                String deltailIds = null;
                String planIds = null;


                for (HfGamePlanDetailDO gmp : gamePlanDetailModelList) {
                    try {
                        int totamount = gmp.getTotamount();
                        Long deltailId = gmp.getId().longValue();
                        Long userId = gmp.getUserId();
                        Long planId = Long.valueOf(gmp.getPlancode());
                        lock = lockTemplate.lock("finance_money" + userId);

                        DreamUFinanceMainDO mainModel = mainDao.findByUserId(userId);
                        if (mainModel == null) {
                            throw new BusinessException(ResponseCode.ERROR_CODE);
                        }
                        Double moneyFrozen = mainModel.getMoneyFrozen().doubleValue();
                        Double moneyUsable = mainModel.getMoneyUsable().doubleValue();
                        // 修改mian表
                        moneyFrozen = Arith.subtract(moneyFrozen, totamount);
                        mainModel.setMoneyFrozen(BigDecimal.valueOf( moneyFrozen));
                        mainDao.update(mainModel);

                        DreamUFinanceFlowDO flowModel = new DreamUFinanceFlowDO();
                        flowModel.setUserId(userId);
                        flowModel.setType(FinanceFlowType.HF_UNFROZEN_GOODS.getType());
                        flowModel.setTypeDetail(FinanceFlowType.HF_UNFROZEN_GOODS.getTypeDetail());
                        flowModel.setRemark(FinanceFlowType.HF_UNFROZEN_GOODS.getRemark());
                        flowModel.setMoney(BigDecimal.valueOf(totamount));
                        flowModel.setCOrderId(userId + "-detailid-" + deltailId);
                        flowModel.setMoneyLeft(BigDecimal.valueOf(moneyUsable));
                        flowDao.save(flowModel);

                        // 更新计划
                        gamePlanDao.updateGamePlan(totamount, null, 1, null, planId);

                        if (null == deltailIds) {
                            deltailIds = String.valueOf(deltailId);
                            planIds = String.valueOf(planId);
                        } else {
                            deltailIds = deltailIds + "," + deltailId;
                            planIds = planIds + "," + planId;
                        }

                    } catch (Exception e) {
                        throw new BusinessException(ResponseCode.ERROR_CODE, "生成计划订单操作冻结错误");
                    } finally {
                        if (lock != null) {
                            lockTemplate.releaseLock(lock);
                            redisTemplate.delete(GameBetRedisKey.BUILD_PLAN_ORDER_LOCK);
                        }
                    }
                }
                try {
                    if (null != deltailIds) {
                        logger.info("追号生成订单的deltailIds  list:{}  planids list {}", deltailIds, planIds);
                        // 生成订单
                        String[] idslist = deltailIds.split("\\,");
                        gamePlanDao.createGameOrderByPlan(idslist);
                        gamePlanDao.updateGamePlanDetail(idslist, GamePlanDetailStatus.EXEC.getValue());
                        // TODO plan finish
                        String[] planIdsList = planIds.split("\\,");
                        gamePlanDao.updateGamePlanByIds(planIdsList, GamePlanStatus.FINISH.getValue());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new BusinessException(ResponseCode.ERROR_CODE, "生成计划订单操作错误");
                } finally {
                    redisTemplate.delete(GameBetRedisKey.BUILD_PLAN_ORDER_LOCK);
                }
                redisTemplate.delete(GameBetRedisKey.BUILD_PLAN_ORDER_LOCK);
            }//end if

        } // end if

        // batch update mainio flow

    }

}
