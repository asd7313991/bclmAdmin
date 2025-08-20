package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamWithdrawTypeService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.withdraw.WithdrawTypeVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;

/**
 * The type Dream withdraw type controller.
 */
@RestController
@RequestMapping("/dream/withdrawType")
@Slf4j
public class DreamWithdrawTypeController {

    @Resource
    private DreamWithdrawTypeService dreamWithdrawTypeService;


    /**
     * 查询提款方式
     *
     * @return common result
     */
    @TLogAspectExt(str = "查询提款规则", moduleName = "WithdrawType", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/selectList")
    public CommonResult selectList() {
        List<WithdrawTypeVO> withdrawMethod = dreamWithdrawTypeService.getWithdrawMethod(null);
        return CommonResult.success(withdrawMethod);
    }
}
