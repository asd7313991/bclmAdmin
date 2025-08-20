package org.example.system.controller.dream;


import com.baomidou.lock.annotation.Lock4j;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.enums.CommonStatusEnum;
import org.example.po.dream.dream.DreamDepositTypeRuleDO;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamDepositTypeRuleService;
import org.example.system.config.log.SystemTLogConvert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.operatelog.core.enums.OperateTypeEnum.UPDATE;

/**
 * The type Dream deposit rule controller.
 */
@RestController
@RequestMapping("/dream/depositRule")
@Slf4j
public class DreamDepositRuleController {

    @Resource
    private DreamDepositTypeRuleService dreamDepositTypeRuleService;


    /**
     * 充值规则 查询
     *
     * @return common result
     */
    @TLogAspectExt(str = "充值规则 查询", moduleName = "DepositRule", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("selectList")
    public CommonResult<List<DreamDepositTypeRuleDO>> selectList() {
        List<DreamDepositTypeRuleDO> depositMethod = dreamDepositTypeRuleService.list(new LambdaQueryWrapperX<DreamDepositTypeRuleDO>().eq(DreamDepositTypeRuleDO::getDeleted, CommonStatusEnum.ENABLE));
        return CommonResult.success(depositMethod);
    }


    /**
     * 更新充值规则
     *
     * @param deposit the deposit
     * @return common result
     */
    @Lock4j(keys = {"#deposit.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "更新充值规则", moduleName = "DepositRule", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("update")
    public CommonResult<Boolean> updateRule(@RequestBody DreamDepositTypeRuleDO deposit) {
        Boolean result = dreamDepositTypeRuleService.updateRule(deposit);
        return CommonResult.success(result);
    }

}
