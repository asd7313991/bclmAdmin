package org.example.service.hf;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.gamePlan.HfGamePlanReqVo;

import java.util.List;

/**
 * @description 针对表【hf_game_plan】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface HfGamePlanService extends IService<HfGamePlanDO> {

    PageResult<HfGamePlanDO> pageByParams(HfGamePlanReqVo pageVO);

    List<HfGamePlanDO> findGamePlan(Integer status, Integer userId, Integer priStatus, Integer id);

    List<HfGamePlanDetailDO> findGamePlanDetailByID(Integer userId, Long planId);

    void updateGamePlan(Integer soldAmount,  Double winAmount, Integer finishIssue,  Integer status, long id);


    void updateGamePlanByStop(String[] idslist, int value);

    void updateGamePlanDetailByPlan(String[] idslist, int value);

    List<HfGamePlanDO> findGamePlanByStatus(int value);

    void createGamePlanDetail(HfGamePlanDetailDO gpd);

    List<HfGamePlanDetailDO> findGamePlanDetail(String lotteryId, String s, int value, String lastIssueCode);

    void createGameOrderByPlan(String[] idslist);

    void updateGamePlanDetail(String[] idslist, int value);

    void updateGamePlanByIds(String[] planIdsList, int value);
}
