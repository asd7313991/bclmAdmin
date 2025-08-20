package org.example.po.mapper.hf;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.GameAnalysisVo;
import org.example.vo.hf.gameOrder.HfGameOrderReqVo;

import java.util.List;
import java.util.Map;

/**
 * @description 针对表【hf_game_order】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.HfGameOrderDO
 */
public interface HfGameOrderMapper extends BaseMapperX<HfGameOrderDO> {

    default PageResult<HfGameOrderDO> selectPage(HfGameOrderReqVo pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<HfGameOrderDO>()
                .eqIfPresent(HfGameOrderDO::getIssuecode, pageVO.getIssuecode())
                .eqIfPresent(HfGameOrderDO::getLotteryid, pageVO.getLotteryid())
                .eqIfPresent(HfGameOrderDO::getOrdercode, pageVO.getOrdercode())
                .eqIfPresent(HfGameOrderDO::getOrderstatus, pageVO.getOrderstatus())
                .eqIfPresent(HfGameOrderDO::getGameplanid, pageVO.getGameplanid())
                .eqIfPresent(HfGameOrderDO::getGameplandetailid, pageVO.getGameplandetailid())
                .eqIfPresent(HfGameOrderDO::getUserid, pageVO.getUserid())
                .betweenIfPresent(HfGameOrderDO::getSaletime, pageVO.getSaletime())
                .betweenIfPresent(HfGameOrderDO::getCalculatewintime, pageVO.getCalculatewintime())
                .betweenIfPresent(HfGameOrderDO::getCreatetime, pageVO.getCreateTime())
                .orderByDesc(HfGameOrderDO::getCreatetime));
    }

    public List<HfGameOrderDO> getOrderList(@Param("lotteryId") String lotteryId, @Param("userId") Integer userId, @Param("status") Integer status);

    public HfGameOrderDO getOrderById(@Param("id") Long id, @Param("userId") Integer userId);

    public HfGameIssueDO findcurIssue(@Param("lotteryId") String lotteryId);

    public List<HfGameIssueDO> findHistoryIssue(@Param("lotteryId") String lotteryId);

    public List<GameAnalysisVo> getOrderAnalysis(@Param("lotteryId") String lotteryId, @Param("userId") Integer userId);

    public void updateGameOrder(@Param("lotteryId") String lotteryId, @Param("winRecords") String[] winRecords, @Param("issueCode") String issueCode);

    public void updateGameOrderByunprize(@Param("lotteryId") String lotteryId, @Param("winRecords") String[] winRecords, @Param("issueCode") String issueCode);

    public List<Map<String, Object>> selectGamePlanByPrize(@Param("lotteryId") String lotteryId, @Param("winRecords") String[] winRecords, @Param("issueCode") String issueCode);

    public List<HfGameOrderDO> getSendIssue(@Param("lotteryId") String lotteryId, @Param("issueCode") String issueCode, @Param("orderStatus") Integer orderStatus);
}




