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
                .eqIfPresent(HfGameIssueDO::getIssueCode, pageVO.getIssuecode())
                .eqIfPresent(HfGameIssueDO::getLotteryid, pageVO.getLotteryid())
                .eqIfPresent(HfGameIssueDO::getNumberRecord, pageVO.getNumberrecord())
                .eqIfPresent(HfGameIssueDO::getSumRecord, pageVO.getSumrecord())
                .eqIfPresent(HfGameIssueDO::getWinRecord, pageVO.getWinrecord())
                .eqIfPresent(HfGameIssueDO::getIssueStatus, pageVO.getIssuestatus())
                .betweenIfPresent(HfGameIssueDO::getOpenDrawTime, pageVO.getOpendrawtime())
                .betweenIfPresent(HfGameIssueDO::getCreatetime, pageVO.getCreateTime())
                .orderByDesc(HfGameIssueDO::getCreatetime);
        if (pageVO.getSaleTime() != null) {
            hfGameIssueDOLambdaQueryWrapperX.geIfPresent(HfGameIssueDO::getSaleStartTime, pageVO.getSaleTime()[0]);
            hfGameIssueDOLambdaQueryWrapperX.leIfPresent(HfGameIssueDO::getSaleEndTime, pageVO.getSaleTime()[1]);

        }
        return selectPage(pageVO, hfGameIssueDOLambdaQueryWrapperX);
    }

    HfGameIssueDO findcurIssue(String lotteryId);


    public List<HfGameIssueDO> findHistoryIssue(@Param("lotteryId") String lotteryId, @Param("issueCode") String issueCode);

//    public int drawGameIssue(@Param("numberRecord") String numberRecord, @Param("sumRecord") String sumRecord, @Param("winRecord") String winRecord, @Param("issue") String issue);

    public void issueOpenClose(@Param("issueStatus") int issueStatus, @Param("lotteryId") String lotteryId, @Param("issueCode") String issueCode);

    public HfGameIssueDO findIssueByStatus(@Param("lotteryId") String lotteryId, @Param("issueStatus") int issueStatus);


    int drawGameIssue(@Param("numberRecord") String numberRecord,
                      @Param("sumRecord")     String sumRecord,
                      @Param("winRecord")     String winRecord,
                      @Param("issue")         String issue);

    // 可选：用于兜底检查
    int countByIssue(@Param("issue") String issue);

    int insertIssueIfAbsent(@org.apache.ibatis.annotations.Param("issue")         String issue,
                            @org.apache.ibatis.annotations.Param("lotteryId")     Long lotteryId,
                            @org.apache.ibatis.annotations.Param("lastIssue")     String lastIssue,
                            @org.apache.ibatis.annotations.Param("saleStartTime") java.util.Date saleStartTime,
                            @org.apache.ibatis.annotations.Param("saleEndTime")   java.util.Date saleEndTime,
                            @org.apache.ibatis.annotations.Param("saleDrawTime")  java.util.Date saleDrawTime);



}






