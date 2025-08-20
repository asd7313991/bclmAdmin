package org.example.service.dream;

import org.example.po.dream.dream.DreamWithdrawTypeRuleDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author a5.0
* @description 针对表【dream_withdraw_type_rule】的数据库操作Service
* @createDate 2023-09-17 09:51:06
*/
public interface DreamWithdrawTypeRuleService extends IService<DreamWithdrawTypeRuleDO> {

    DreamWithdrawTypeRuleDO selectByWithdrawTypeId(Long withdrawId);

    Boolean updateRule(DreamWithdrawTypeRuleDO deposit);
}
