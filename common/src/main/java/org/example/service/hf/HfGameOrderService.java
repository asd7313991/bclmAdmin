package org.example.service.hf;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.GameAnalysisVo;
import org.example.vo.hf.gameOrder.HfGameOrderReqVo;

import java.util.List;
import java.util.Map;

/**
 * @author D588
 * @description 针对表【hf_game_order】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface HfGameOrderService extends IService<HfGameOrderDO> {

    PageResult<HfGameOrderDO> pageByParams(HfGameOrderReqVo pageVO);

    List<HfGameOrderDO> getOrderList(String lotteryId, Integer userId, Integer status);

    HfGameOrderDO getOrderById(Long id, Integer userId);

    List<GameAnalysisVo> getOrderAnalysis(String lotteryId, Integer userId);


    void updateGameOrder(String lotteryId, String[] winRecords, String issueCode);
    void updateGameOrderByunprize(String lotteryId, String[] winRecords, String issueCode);
    List<Map<String, Object>> selectGamePlanByPrize(String lotteryId, String[] winRecords, String issueCode);
    List<HfGameOrderDO> getSendIssue(String lotteryId, String issueCode, int value);

}
