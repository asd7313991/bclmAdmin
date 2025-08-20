package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamDepositTypeService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.deposit.DepositTypeVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;

/**
 * The type Dream deposit type controller.
 */
@RestController
@RequestMapping("/dream/depositType")
@Slf4j
public class DreamDepositTypeController {

    @Resource
    private DreamDepositTypeService depositTypeService;


    /**
     * 充值支付类型查询
     *
     * @return common result
     */
    @TLogAspectExt(str = "充值支付类型 查询", moduleName = "DepositType", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("selectList")
    public CommonResult<List<DepositTypeVO>> selectList() {

        List<DepositTypeVO> depositMethod = depositTypeService.getDepositMethod();
        return CommonResult.success(depositMethod);
    }

}
