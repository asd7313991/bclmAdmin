package org.example.service.hf.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.po.mapper.hf.HfGameOrderMapper;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGameOrderService;
import org.example.vo.hf.GameAnalysisVo;
import org.example.vo.hf.gameOrder.HfGameOrderReqVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author D588
 * @description 针对表【hf_game_order】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service("hfGameOrderService")
public class HfGameOrderServiceImpl extends ServiceImpl<HfGameOrderMapper, HfGameOrderDO>
        implements HfGameOrderService {

    @Override
    public PageResult<HfGameOrderDO> pageByParams(HfGameOrderReqVo pageVO) {
        return getBaseMapper().selectPage(pageVO);
    }


    @Override
    public List<HfGameOrderDO> getOrderList(String lotteryId, Integer userId, Integer status) {
        return getBaseMapper().getOrderList(lotteryId, userId, status);
    }


    @Override
    public HfGameOrderDO getOrderById(Long id, Integer userId) {
        return getBaseMapper().getOrderById(id, userId);
    }


    @Override
    public List<GameAnalysisVo> getOrderAnalysis(String lotteryId, Integer userId) {
        return getBaseMapper().getOrderAnalysis(lotteryId, userId);
    }

    @Override
    public void updateGameOrder(String lotteryId, String[] winRecords, String issueCode) {
        getBaseMapper().updateGameOrder(lotteryId,winRecords,issueCode);
    }

    @Override
    public void updateGameOrderByunprize(String lotteryId, String[] winRecords, String issueCode) {
        getBaseMapper().updateGameOrderByunprize(lotteryId,winRecords,issueCode);
    }

    @Override
    public List<Map<String, Object>> selectGamePlanByPrize(String lotteryId, String[] winRecords, String issueCode) {
        return getBaseMapper().selectGamePlanByPrize(lotteryId,winRecords,issueCode);
    }

    @Override
    public List<HfGameOrderDO> getSendIssue(String lotteryId, String issueCode, int value) {
        return getBaseMapper().getSendIssue(lotteryId,issueCode,value);

    }

}




