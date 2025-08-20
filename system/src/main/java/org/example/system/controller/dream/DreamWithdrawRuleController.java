package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.dream.DreamWithdrawTypeRuleDO;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamWithdrawTypeRuleService;
import org.example.system.config.log.SystemTLogConvert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.operatelog.core.enums.OperateTypeEnum.UPDATE;

/**
 * The type Dream withdraw rule controller.
 */
@RestController
@RequestMapping("/dream/withdrawRule")
@Slf4j
public class DreamWithdrawRuleController {

    @Resource
    private DreamWithdrawTypeRuleService dreamWithdrawTypeRuleService;


    /**
     * 查询提款规则
     *
     * @return common result
     */
    @TLogAspectExt(str = "查询提款规则", moduleName = "WithdrawRule", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("selectList")
    public CommonResult<List<DreamWithdrawTypeRuleDO>> selectList() {
        List<DreamWithdrawTypeRuleDO> depositMethod = dreamWithdrawTypeRuleService.list();
        return CommonResult.success(depositMethod);
    }


    /**
     * 更新提款规则
     *
     * @param deposit the deposit
     * @return common result
     */
    @TLogAspectExt(str = "查询提款规则", moduleName = "WithdrawRule", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("update")
    public CommonResult<Boolean> updateRule(@RequestBody DreamWithdrawTypeRuleDO deposit) {
        Boolean result = dreamWithdrawTypeRuleService.updateRule(deposit);
        return CommonResult.success(result);
    }
}
