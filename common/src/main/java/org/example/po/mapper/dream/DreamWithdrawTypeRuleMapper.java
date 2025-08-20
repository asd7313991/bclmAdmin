package org.example.po.mapper.dream;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamWithdrawTypeRuleDO;

import java.util.List;

/**
 * @author a5.0
 * @description 针对表【dream_withdraw_type_rule】的数据库操作Mapper
 * @createDate 2023-09-17 09:51:06
 * @Entity org.example.po.dream.dream.DreamWithdrawTypeRuleDO
 */
public interface DreamWithdrawTypeRuleMapper extends BaseMapper<DreamWithdrawTypeRuleDO> {

    default List<DreamWithdrawTypeRuleDO> selectByWithdrawTypeIds(List<Long> collect) {
        return selectList(new LambdaQueryWrapperX<DreamWithdrawTypeRuleDO>()
                .in(DreamWithdrawTypeRuleDO::getWithdrawTypeId, collect)
                .eq(DreamWithdrawTypeRuleDO::getStatus, CommonStatusEnum.ENABLE)
                .orderByDesc(DreamWithdrawTypeRuleDO::getCreateTime));
    }
}




