package org.example.po.mapper.dream;

import org.apache.ibatis.annotations.Param;
import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUFinanceMainDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;
import org.example.vo.dream.financeMain.DreamUFinancePageReqVO;

/**
 * @description 针对表【dream_u_finance_main】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUFinanceMainDO
 */
public interface DreamUFinanceMainMapper extends BaseMapperX<DreamUFinanceMainDO> {

    PageResult<DreamUFinanceMainVO> selectPageByVo(DreamUFinancePageReqVO pageReqVO, @Param("vo") DreamUFinancePageReqVO pageVO);

    default PageResult<DreamUFinanceMainDO> selectPage(DreamUFinancePageReqVO pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceMainDO>()
                .eqIfPresent(DreamUFinanceMainDO::getUserId, pageVO.getUserId())
                .betweenIfPresent(DreamUFinanceMainDO::getCreateTime, pageVO.getCreateTime())
                .betweenIfPresent(DreamUFinanceMainDO::getMoneyUsable, pageVO.getMoneyUsable())
                .betweenIfPresent(DreamUFinanceMainDO::getMoneyFrozen, pageVO.getMoneyFrozen())
                .betweenIfPresent(DreamUFinanceMainDO::getMargin, pageVO.getMargin())
                .betweenIfPresent(DreamUFinanceMainDO::getMoneyDrawUsable, pageVO.getMoneyDrawUsable())
                .orderByDesc(DreamUFinanceMainDO::getUpdateTime));
    }

//    default PageResult<DreamUFinanceMainDO> selectPage1(DreamUFinancePageReqVO pageVO) {
//        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceMainDO>()
//                .eqIfPresent(DreamUFinanceMainDO::getUserId, pageVO.getUserId())
//                .betweenIfPresent(DreamUFinanceMainDO::getCreateTime, pageVO.getCreateTime())
//                .betweenIfPresent(DreamUFinanceMainDO::getMoneyUsable, pageVO.getMoneyUsable())
//                .betweenIfPresent(DreamUFinanceMainDO::getMoneyFrozen, pageVO.getMoneyFrozen())
//                .betweenIfPresent(DreamUFinanceMainDO::getMargin, pageVO.getMargin())
//                .betweenIfPresent(DreamUFinanceMainDO::getMoneyDrawUsable, pageVO.getMoneyDrawUsable())
//                .orderByDesc(DreamUFinanceMainDO::getCreateTime));
//    }


}




