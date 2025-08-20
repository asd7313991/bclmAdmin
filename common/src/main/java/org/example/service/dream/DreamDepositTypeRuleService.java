package org.example.service.dream;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.dream.dream.DreamDepositTypeRuleDO;

import java.util.List;

/**
* @author Administrator
* @description 针对表【dream_deposit_type_rule】的数据库操作Service
* @createDate 2023-09-17 03:12:43
*/
public interface DreamDepositTypeRuleService extends IService<DreamDepositTypeRuleDO> {

    List<DreamDepositTypeRuleDO> selectByDepositTypeIds(List<Long> collect);


    DreamDepositTypeRuleDO selectById(Long ruleId, Long depositTypeId);

    DreamDepositTypeRuleDO selectByDepositTypeId(Long depositTypeId);

    Boolean updateRule(DreamDepositTypeRuleDO deposit);
}
