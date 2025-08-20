package org.example.service.hf;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.example.po.dream.hf.HfGamePlanDetailDO;

import java.util.List;

/**
 * @description 针对表【hf_game_plan_detail】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface HfGamePlanDetailService extends IService<HfGamePlanDetailDO> {
    List<HfGamePlanDetailDO> findGamePlanDetail(String lotteryId, String s, int value, String lastIssueCode);
    void updateGamePlanDetail(String[] ids, int detailStatus);
}
