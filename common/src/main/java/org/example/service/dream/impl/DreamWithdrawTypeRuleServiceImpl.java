package org.example.service.dream.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamWithdrawTypeRuleDO;
import org.example.service.dream.DreamWithdrawTypeRuleService;
import org.example.po.mapper.dream.DreamWithdrawTypeRuleMapper;
import org.springframework.stereotype.Service;

/**
* @author a5.0
* @description 针对表【dream_withdraw_type_rule】的数据库操作Service实现
* @createDate 2023-09-17 09:51:06
*/
@Service
public class DreamWithdrawTypeRuleServiceImpl extends ServiceImpl<DreamWithdrawTypeRuleMapper, DreamWithdrawTypeRuleDO>
    implements DreamWithdrawTypeRuleService{

    @Override
    public DreamWithdrawTypeRuleDO selectByWithdrawTypeId(Long withdrawId) {
        return getOne(new LambdaQueryWrapperX<DreamWithdrawTypeRuleDO>()
                .eq(DreamWithdrawTypeRuleDO::getWithdrawTypeId,withdrawId)
                .eq(DreamWithdrawTypeRuleDO::getStatus, CommonStatusEnum.ENABLE));
    }


    @Override
    public Boolean updateRule(DreamWithdrawTypeRuleDO deposit) {
        return getBaseMapper().updateById(deposit)>0;
    }
}




