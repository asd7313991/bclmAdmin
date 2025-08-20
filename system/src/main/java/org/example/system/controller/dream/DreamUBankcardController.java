package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.dream.DreamUUserBankcardDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUUserBankcardService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.bankcard.DreamUserBankcardReqVo;
import org.example.vo.dream.bankcard.DreamUserBankcardVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream u bankcard controller.
 */
@RestController
@RequestMapping("/dream/userBankcard")
@Slf4j
public class DreamUBankcardController {

    @Resource
    private DreamUUserBankcardService uUserBankcardService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 暂时未使用到
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "用户银行卡记录查询", moduleName = "FinanceBankcard", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUserBankcardVO>> getBannerPage(@Valid DreamUserBankcardReqVo pageVO) {
        PageResult<DreamUUserBankcardDO> pageResult = uUserBankcardService.pageByParams(pageVO);
        PageResult<DreamUserBankcardVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), DreamUserBankcardVO.class));
        return success(empty);
    }


}
