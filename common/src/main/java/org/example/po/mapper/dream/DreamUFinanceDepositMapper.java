package org.example.po.mapper.dream;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUFinanceDepositDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.financeDeposit.DreamUFinanceDepositPageReqVO;

/**
 * @description 针对表【dream_u_finance_deposit】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUFinanceDepositDO
 */
public interface DreamUFinanceDepositMapper extends BaseMapperX<DreamUFinanceDepositDO> {

    default PageResult<DreamUFinanceDepositDO> selectPage(DreamUFinanceDepositPageReqVO pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUFinanceDepositDO>()
                .eqIfPresent(DreamUFinanceDepositDO::getId, pageVO.getId())
                .eqIfPresent(DreamUFinanceDepositDO::getUserId, pageVO.getUserId())
                .eqIfPresent(DreamUFinanceDepositDO::getType, pageVO.getType())
                .eqIfPresent(DreamUFinanceDepositDO::getSubmitOrderId, pageVO.getSubmitOrderId())
                .eqIfPresent(DreamUFinanceDepositDO::getIoId, pageVO.getIoId())
                .eqIfPresent(DreamUFinanceDepositDO::getStatus, pageVO.getStatus())
                .betweenIfPresent(DreamUFinanceDepositDO::getCreateTime, pageVO.getCreateTime())
                .orderByDesc(DreamUFinanceDepositDO::getCreateTime));
    }
}




