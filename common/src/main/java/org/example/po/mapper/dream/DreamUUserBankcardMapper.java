package org.example.po.mapper.dream;

import org.example.config.mybatisplus.BaseMapperX;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.dream.DreamUUserBankcardDO;
import org.example.pojo.PageResult;
import org.example.vo.dream.bankcard.DreamUserBankcardReqVo;

/**
 * @description 针对表【dream_u_user_bankcard】的数据库操作Mapper
 * @createDate 2023-09-13 13:32:04
 * @Entity generator.domain.DreamUUserBankcardDO
 */
public interface DreamUUserBankcardMapper extends BaseMapperX<DreamUUserBankcardDO> {

    default PageResult<DreamUUserBankcardDO> selectPage(DreamUserBankcardReqVo pageVO) {
        return selectPage(pageVO, new LambdaQueryWrapperX<DreamUUserBankcardDO>()
                .eqIfPresent(DreamUUserBankcardDO::getUserId, pageVO.getUserId())
                .likeIfPresent(DreamUUserBankcardDO::getRealName, pageVO.getRealName())
                .eqIfPresent(DreamUUserBankcardDO::getIdCard, pageVO.getIdCard())
                .eqIfPresent(DreamUUserBankcardDO::getIdStatus, pageVO.getIdStatus())
                .eqIfPresent(DreamUUserBankcardDO::getBankId, pageVO.getBankId())
                .eqIfPresent(DreamUUserBankcardDO::getBindStatus, pageVO.getBindStatus())
                .eqIfPresent(DreamUUserBankcardDO::getCardNumber, pageVO.getCardNumber())
                .eqIfPresent(DreamUUserBankcardDO::getCardPhone, pageVO.getCardPhone())
                .eqIfPresent(DreamUUserBankcardDO::getCardState, pageVO.getCardState())
                .betweenIfPresent(DreamUUserBankcardDO::getCreateTime, pageVO.getCreateTime())
                .orderByDesc(DreamUUserBankcardDO::getCreateTime));
    }
}




