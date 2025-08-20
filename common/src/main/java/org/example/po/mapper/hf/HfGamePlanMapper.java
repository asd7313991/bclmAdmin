package org.example.po.mapper.hf;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.gamePlan.HfGamePlanReqVo;

import java.util.List;

/**
 * @description 针对表【hf_game_plan】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.HfGamePlanDO
 */
public interface HfGamePlanMapper extends BaseMapperX<HfGamePlanDO> {

    default PageResult<HfGamePlanDO> selectPage(HfGamePlanReqVo pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<HfGamePlanDO>()
                .eqIfPresent(HfGamePlanDO::getLotteryid, pageVO.getLotteryid())
                .eqIfPresent(HfGamePlanDO::getStartisuuecode, pageVO.getStartisuuecode())
                .eqIfPresent(HfGamePlanDO::getFinishissue, pageVO.getFinishissue())
                .eqIfPresent(HfGamePlanDO::getTotalissue, pageVO.getTotalissue())
                .eqIfPresent(HfGamePlanDO::getFinishissue, pageVO.getFinishissue())
                .eqIfPresent(HfGamePlanDO::getTotalissue, pageVO.getTotalissue())
                .eqIfPresent(HfGamePlanDO::getStopmode, pageVO.getStopmode())
                .eqIfPresent(HfGamePlanDO::getPlantype, pageVO.getPlantype())
                .eqIfPresent(HfGamePlanDO::getPlancode, pageVO.getPlancode())
                .eqIfPresent(HfGamePlanDO::getStatus, pageVO.getStatus())
                .eqIfPresent(HfGamePlanDO::getUserid, pageVO.getUserid())
                .betweenIfPresent(HfGamePlanDO::getTotamount, pageVO.getTotamount())
                .betweenIfPresent(HfGamePlanDO::getSoldamount, pageVO.getSoldamount())
                .betweenIfPresent(HfGamePlanDO::getWinamount, pageVO.getWinamount())
                .betweenIfPresent(HfGamePlanDO::getCreatetime, pageVO.getCreateTime())
                .orderByDesc(HfGamePlanDO::getCreatetime));
    }

    public List<HfGamePlanDO> findGamePlan(@Param("status") Integer status, @Param("userId") Integer userId, @Param("priStatus") Integer priStatus, @Param("id") Long id);

    List<HfGamePlanDetailDO> findGamePlanDetailByID(@Param("userId") Integer userId, @Param("planId") Long planId);
    void updateGamePlan(@Param("soldAmount") Integer soldAmount,@Param("winAmount") Double winAmount,@Param("finishIssue") Integer finishIssue,@Param("status") Integer status,@Param("id") long id);

     void updateGamePlanByStop(@Param("ids") String[] ids,@Param("status") int status);
     void updateGamePlanDetailByPlan(@Param("ids") String[] ids,@Param("detailStatus") int detailStatus);
    List<HfGamePlanDO> findGamePlanByStatus(@Param("status") Integer status);
    void createGameOrderByPlan(@Param("ids") String[] ids);
    void updateGamePlanByIds(@Param("ids") String[] ids,@Param("status") int status);

}




