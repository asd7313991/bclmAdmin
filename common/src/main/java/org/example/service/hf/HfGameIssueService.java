package org.example.service.hf;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.gameIssue.HfGameIssueReqVo;
import org.example.vo.hf.gameIssue.KenoDrawThirdDTO;

import java.util.List;

/**
 * @description 针对表【hf_game_issue】的数据库操作Service
 * @createDate 2023-09-13 13:32:04
 */
public interface HfGameIssueService extends IService<HfGameIssueDO> {

    PageResult<HfGameIssueDO> pageByParams(HfGameIssueReqVo pageVO);

    void push(KenoDrawThirdDTO kenoDrawThirdDTO);

    HfGameIssueDO findcurIssue(String lotteryId);

    List<HfGameIssueDO> findHistoryIssue(String lotteryId, String issueCode);

    void drawGameIssue(String numberRecord, String sumRecord, String winRecord, String issue);

    void issueOpenClose(int value, String lotteryId, String issueCode);

    HfGameIssueDO findIssueByStatus(String lotteryId, int value);
}
