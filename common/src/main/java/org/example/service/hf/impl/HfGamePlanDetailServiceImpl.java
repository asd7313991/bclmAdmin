package org.example.service.hf.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.po.mapper.hf.HfGamePlanDetailMapper;
import org.example.service.hf.HfGamePlanDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author D588
 * @description 针对表【hf_game_plan_detail】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service("hfGamePlanDetailServiceImpl")
public class HfGamePlanDetailServiceImpl extends ServiceImpl<HfGamePlanDetailMapper, HfGamePlanDetailDO>
        implements HfGamePlanDetailService {

    @Override
    public List<HfGamePlanDetailDO> findGamePlanDetail(String lotteryId, String s, int value, String lastIssueCode) {
        return getBaseMapper().findGamePlanDetail(lotteryId,s,value,lastIssueCode);
    }

    @Override
    public void updateGamePlanDetail(String[] ids, int detailStatus) {
        getBaseMapper().updateGamePlanDetail(ids,detailStatus);
    }
}




