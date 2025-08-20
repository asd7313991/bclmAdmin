package org.example.system.controller.dream;


import com.baomidou.lock.annotation.Lock4j;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamDepositVirAddressService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.DreamDepositVirAddressVO;
import org.example.vo.dream.DepositVirAddressVO;
import org.example.vo.dream.withdraw.DepositVirAddressReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.example.operatelog.core.enums.OperateTypeEnum.*;

/**
 * The type Dream deposit vir address controller.
 */
@RestController
@RequestMapping("/dream/depositVirAddress")
@Slf4j
public class DreamDepositVirAddressController {


    @Resource
    private DreamDepositVirAddressService dreamDepositVirAddressService;


    /**
     * 充值支付地址查询
     *
     * @param depositVirAddressVO the deposit vir address vo
     * @return common result
     */
    @TLogAspectExt(str = "充值支付地址查询", moduleName = "DepositVirAddress", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamDepositVirAddressVO>> select(DepositVirAddressVO depositVirAddressVO) {
        PageResult<DreamDepositVirAddressVO> pageResult = dreamDepositVirAddressService.pageByParams(depositVirAddressVO);
        return CommonResult.success(pageResult);
    }


    /**
     * Update common result.
     *
     * @param depositVirAddressVO the deposit vir address vo
     * @return the common result
     */
    @Lock4j(keys = {"#depositVirAddressVO.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "充值支付地址更新", moduleName = "DepositVirAddress", type = UPDATE, convert = SystemTLogConvert.class)
    @PostMapping("/update")
    public CommonResult<Boolean> update(@RequestBody DepositVirAddressReq depositVirAddressVO) {
        Boolean result = dreamDepositVirAddressService.update(depositVirAddressVO);
        return CommonResult.success(result);
    }


    /**
     * 创建充值支付地址
     *
     * @param depositVirAddressVO the deposit vir address vo
     * @return common result
     */
    @TLogAspectExt(str = "创建充值支付地址", moduleName = "DepositVirAddress", type = CREATE, convert = SystemTLogConvert.class)
    @PostMapping("/create")
    public CommonResult<Boolean> create(@RequestBody DepositVirAddressReq depositVirAddressVO) {
        Boolean result = dreamDepositVirAddressService.create(depositVirAddressVO);
        return CommonResult.success(result);
    }
}
