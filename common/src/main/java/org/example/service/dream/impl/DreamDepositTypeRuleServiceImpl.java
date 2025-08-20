package org.example.service.dream.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamDepositTypeRuleDO;
import org.example.service.dream.DreamDepositTypeRuleService;
import org.example.po.mapper.dream.DreamDepositTypeRuleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【dream_deposit_type_rule】的数据库操作Service实现
 * @createDate 2023-09-17 03:12:43
 */
@Slf4j
@Service
public class DreamDepositTypeRuleServiceImpl extends ServiceImpl<DreamDepositTypeRuleMapper, DreamDepositTypeRuleDO>
        implements DreamDepositTypeRuleService {

    @Override
    public List<DreamDepositTypeRuleDO> selectByDepositTypeIds(List<Long> collect) {
        return getBaseMapper().selectList(new LambdaQueryWrapperX<DreamDepositTypeRuleDO>()
                .in(DreamDepositTypeRuleDO::getDepositTypeId, collect)
                .eq(DreamDepositTypeRuleDO::getStatus, CommonStatusEnum.ENABLE));
    }

    @Override
    public DreamDepositTypeRuleDO selectById(Long ruleId, Long depositTypeId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamDepositTypeRuleDO>()
                .eq(DreamDepositTypeRuleDO::getId, ruleId)
                .eq(DreamDepositTypeRuleDO::getDepositTypeId, depositTypeId)
                .eq(DreamDepositTypeRuleDO::getStatus, CommonStatusEnum.ENABLE));
    }

    @Override
    public DreamDepositTypeRuleDO selectByDepositTypeId(Long depositTypeId) {
        return getBaseMapper().selectOne(new LambdaQueryWrapperX<DreamDepositTypeRuleDO>()
                .eq(DreamDepositTypeRuleDO::getDepositTypeId, depositTypeId)
                .eq(DreamDepositTypeRuleDO::getStatus, CommonStatusEnum.ENABLE));
    }


    @Override
    public Boolean updateRule(DreamDepositTypeRuleDO deposit) {
        return getBaseMapper().updateById(deposit) > 0;
    }
}




