package org.example.po.mapper.hf;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.po.dream.hf.HfGamePlanDetailDO;

import java.util.List;

/**
 * @description 针对表【hf_game_plan_detail】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.HfGamePlanDetailDO
 */
public interface HfGamePlanDetailMapper extends BaseMapperX<HfGamePlanDetailDO> {
     List<HfGamePlanDetailDO> findGamePlanDetail(@Param("lotteryId") String lotteryId, @Param("gameIssue") String gameIssue, @Param("detailStatus") Integer detailStatus, @Param("lastIssueCode") String lastIssueCode);
     void updateGamePlanDetail(@Param("ids") String[] ids,@Param("detailStatus") int detailStatus);
}




