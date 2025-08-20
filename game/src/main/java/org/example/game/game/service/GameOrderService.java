package org.example.game.game.service;


import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.vo.hf.GameAnalysisVo;

import java.util.List;
import java.util.Map;

/**
 * Created by yhj on 17/6/27.
 */
public interface GameOrderService {


    void gameBet(Integer userId, String issueCode, String lotteryId, Long totalAmount, Integer numberRecord, Double preWinNum);

    void gamePlan(Integer userId, String lotteryId, Integer planType, Integer numberRecord, String startIsuueCode, Integer startMutiple, Integer totalIssue, Integer issueRato, Integer winRate, Integer stopMode, Integer startAmount, long totamount, Double preWinNum);

    HfGameIssueDO findcurIssue(String lotteryId);

    List<HfGameIssueDO> findHistoryIssue(String lotteryId, String issueCode);

    List<HfGameOrderDO> getOrderList(String lotteryId, Integer userId, Integer status);

    List<GameAnalysisVo> getOrderAnalysis(String lotteryId, Integer userId);

    void creatIssue(long issueDate, long issueNum);

    HfGameOrderDO getOrderById(Long id, Integer userId);

    List<HfGamePlanDO> findGamePlan(Integer status, Integer userId, Integer priStatus);

    List<HfGamePlanDetailDO> findGamePlanDetailByID(Integer userId, Long planId);

    void updateGameOrder(String lotteryId, String[] winRecords, String issueCode);

    void updateGameOrderByunprize(String lotteryId, String[] winRecords, String issueCode);

    List<Map<String, Object>> selectGamePlanByPrize(String lotteryId, String[] winRecords, String issueCode);


    List<HfGameOrderDO> getSendIssue(String lotteryId, String issueCode, int value);
}
