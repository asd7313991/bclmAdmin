package org.example.po.mapper.hf;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.pojo.PageResult;
import org.example.vo.hf.gameIssue.HfGameIssueReqVo;

import java.util.List;

/**
 * @description 针对表【hf_game_issue】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.HfGameIssueDO
 */
public interface HfGameIssueMapper extends BaseMapperX<HfGameIssueDO> {

    default PageResult<HfGameIssueDO> selectPage(HfGameIssueReqVo pageVO) {
        LambdaQueryWrapperX<HfGameIssueDO> hfGameIssueDOLambdaQueryWrapperX = new LambdaQueryWrapperX<HfGameIssueDO>()
                .eqIfPresent(HfGameIssueDO::getIssuecode, pageVO.getIssuecode())
                .eqIfPresent(HfGameIssueDO::getLotteryid, pageVO.getLotteryid())
                .eqIfPresent(HfGameIssueDO::getNumberrecord, pageVO.getNumberrecord())
                .eqIfPresent(HfGameIssueDO::getSumrecord, pageVO.getSumrecord())
                .eqIfPresent(HfGameIssueDO::getWinrecord, pageVO.getWinrecord())
                .eqIfPresent(HfGameIssueDO::getIssuestatus, pageVO.getIssuestatus())
                .betweenIfPresent(HfGameIssueDO::getOpendrawtime, pageVO.getOpendrawtime())
                .betweenIfPresent(HfGameIssueDO::getCreatetime, pageVO.getCreateTime())
                .orderByDesc(HfGameIssueDO::getCreatetime);
        if (pageVO.getSaleTime() != null) {
            hfGameIssueDOLambdaQueryWrapperX.geIfPresent(HfGameIssueDO::getSalestarttime, pageVO.getSaleTime()[0]);
            hfGameIssueDOLambdaQueryWrapperX.leIfPresent(HfGameIssueDO::getSaleendtime, pageVO.getSaleTime()[1]);

        }
        return selectPage(pageVO, hfGameIssueDOLambdaQueryWrapperX);
    }

    HfGameIssueDO findcurIssue(String lotteryId);


    public List<HfGameIssueDO> findHistoryIssue(@Param("lotteryId") String lotteryId, @Param("issueCode") String issueCode);

    public void drawGameIssue(@Param("numberRecord") String numberRecord, @Param("sumRecord") String sumRecord, @Param("winRecord") String winRecord, @Param("issue") String issue);

    public void issueOpenClose(@Param("issueStatus") int issueStatus, @Param("lotteryId") String lotteryId, @Param("issueCode") String issueCode);

    public HfGameIssueDO findIssueByStatus(@Param("lotteryId") String lotteryId, @Param("issueStatus") int issueStatus);
}




