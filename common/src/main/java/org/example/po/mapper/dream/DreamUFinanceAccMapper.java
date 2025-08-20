package org.example.po.mapper.dream;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUFinanceAccDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeAcc.DreamUFinanceAccReqVo;

/**
 * @description 针对表【dream_u_finance_acc】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUFinanceAccDO
 */
public interface DreamUFinanceAccMapper extends BaseMapperX<DreamUFinanceAccDO> {

    default PageResult<DreamUFinanceAccDO> pageByParams(DreamUFinanceAccReqVo pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceAccDO>()
                .eqIfPresent(DreamUFinanceAccDO::getUserId, pageVO.getUserId())
                .betweenIfPresent(DreamUFinanceAccDO::getCreateTime, pageVO.getCreateTime())
//                .betweenIfPresent(DreamUFinanceAccDO::getMoneyUsable, pageVO.getMoneyUsable())
//                .betweenIfPresent(DreamUFinanceAccDO::getMoneyFrozen, pageVO.getMoneyFrozen())
//                .betweenIfPresent(DreamUFinanceAccDO::getMargin, pageVO.getMargin())
//                .betweenIfPresent(DreamUFinanceAccDO::getMoneyDrawUsable, pageVO.getMoneyDrawUsable())
                .orderByDesc(DreamUFinanceAccDO::getCreateTime));
    }
}




