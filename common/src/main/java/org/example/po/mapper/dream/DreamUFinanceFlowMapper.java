package org.example.po.mapper.dream;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUFinanceFlowDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeFlow.DreamUFinanceFlowReqVo;

/**
 * @description 针对表【dream_u_finance_flow】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUFinanceFlowDO
 */
public interface DreamUFinanceFlowMapper extends BaseMapperX<DreamUFinanceFlowDO> {

    default PageResult<DreamUFinanceFlowDO> selectPage(DreamUFinanceFlowReqVo pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceFlowDO>()
                .eqIfPresent(DreamUFinanceFlowDO::getId, pageVO.getId())
                .eqIfPresent(DreamUFinanceFlowDO::getUserId, pageVO.getUserId())
                .eqIfPresent(DreamUFinanceFlowDO::getType, pageVO.getType())
                .eqIfPresent(DreamUFinanceFlowDO::getPlatformId, pageVO.getPlatformId())
                .eqIfPresent(DreamUFinanceFlowDO::getCOrderId, pageVO.getCOrderId())
                .eqIfPresent(DreamUFinanceFlowDO::getIoOrderId, pageVO.getIoOrderId())
                .eqIfPresent(DreamUFinanceFlowDO::getCouponsId, pageVO.getCouponsId())
                .betweenIfPresent(DreamUFinanceFlowDO::getCreateTime, pageVO.getCreateTime())
                .orderByDesc(DreamUFinanceFlowDO::getCreateTime));
    }
}




