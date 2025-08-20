package org.example.service.hf.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.po.dream.hf.HfGamePlanDetailDO;
import org.example.po.mapper.hf.HfGamePlanMapper;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGameOrderService;
import org.example.service.hf.HfGamePlanDetailService;
import org.example.service.hf.HfGamePlanService;
import org.example.vo.hf.gamePlan.HfGamePlanReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author D588
 * @description 针对表【hf_game_plan】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service("hfGamePlanServiceImpl")
public class HfGamePlanServiceImpl extends ServiceImpl<HfGamePlanMapper, HfGamePlanDO>
        implements HfGamePlanService {

    @Autowired
    protected HfGamePlanDetailService hfGamePlanDetailService;

    @Override
    public PageResult<HfGamePlanDO> pageByParams(HfGamePlanReqVo pageVO) {
        return getBaseMapper().selectPage(pageVO);
    }


    @Override
    public List<HfGamePlanDO> findGamePlan(Integer status, Integer userId, Integer priStatus, Integer id) {
        return getBaseMapper().findGamePlan(status, userId, priStatus, Long.valueOf(id));
    }

    @Override
    public List<HfGamePlanDetailDO> findGamePlanDetailByID(Integer userId, Long planId) {
        return getBaseMapper().findGamePlanDetailByID(userId, planId);
    }

    @Override
    public void updateGamePlan(Integer soldAmount, Double winAmount, Integer finishIssue, Integer status, long id) {
        getBaseMapper().updateGamePlan(soldAmount,winAmount,finishIssue,status,id);
    }

    @Override
    public void updateGamePlanByStop(String[] idslist, int value) {
        getBaseMapper().updateGamePlanByStop(idslist,value);
    }

    @Override
    public void updateGamePlanDetailByPlan(String[] idslist, int value) {
        getBaseMapper().updateGamePlanDetailByPlan(idslist,value);
    }

    @Override
    public List<HfGamePlanDO> findGamePlanByStatus(int value) {
        return getBaseMapper().findGamePlanByStatus(value);
    }

    @Override
    public void createGamePlanDetail(HfGamePlanDetailDO gpd) {
        hfGamePlanDetailService.save(gpd);
    }

    @Override
    public List<HfGamePlanDetailDO> findGamePlanDetail(String lotteryId, String s, int value, String lastIssueCode) {
        return hfGamePlanDetailService.findGamePlanDetail(lotteryId,s,value,lastIssueCode);
    }

    @Override
    public void createGameOrderByPlan(String[] idslist) {
        getBaseMapper().createGameOrderByPlan(idslist);
    }

    @Override
    public void updateGamePlanDetail(String[] idslist, int value) {
        hfGamePlanDetailService.updateGamePlanDetail(idslist,value);
    }

    @Override
    public void updateGamePlanByIds(String[] planIdsList, int value) {
        getBaseMapper().updateGamePlanByIds(planIdsList,value);
    }


}




