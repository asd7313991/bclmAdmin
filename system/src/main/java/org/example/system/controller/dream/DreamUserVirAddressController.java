package org.example.system.controller.dream;


import com.baomidou.lock.annotation.Lock4j;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.dream.DreamUVirAddressDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUVirAddressService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.DepositVirAddressVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.operatelog.core.enums.OperateTypeEnum.UPDATE;

/**
 * The type Dream user vir address controller.
 */
@RestController
@RequestMapping("/dream/userVirAddress")
@Slf4j
public class DreamUserVirAddressController {


    @Resource
    private DreamUVirAddressService dreamUVirAddressService;

    /**
     * 用户绑定虚拟币地址
     *
     * @param depositVirAddressVO the deposit vir address vo
     * @return common result
     */
    @TLogAspectExt(str = "用户虚拟币地址查询", moduleName = "userVirAddress", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUVirAddressDO>> pageByParams(DepositVirAddressVO depositVirAddressVO) {
        PageResult<DreamUVirAddressDO> pageResult = dreamUVirAddressService.pageByParams(depositVirAddressVO);
        return CommonResult.success(pageResult);
    }


    /**
     * 虚拟币地址审核 （未使用）
     *
     * @param depositVirAddressVO the deposit vir address vo
     * @return common result
     */
    @Lock4j(keys = {"#depositVirAddressVO.id"}, acquireTimeout = 0, expire = 3000, autoRelease = false)
    @TLogAspectExt(str = "虚拟币地址审核", moduleName = "userVirAddress", type = UPDATE, convert = SystemTLogConvert.class)
    @GetMapping("/audit")
    public CommonResult<Boolean> audit(DepositVirAddressVO depositVirAddressVO) {
        Boolean result = dreamUVirAddressService.audit(depositVirAddressVO);
        return CommonResult.success(result);
    }


}
