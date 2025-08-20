package org.example.system.controller.dream;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.dream.DreamUFinanceAccDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.dream.DreamUFinanceAccService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.dream.financeAcc.DreamUFinanceAccReqVo;
import org.example.vo.dream.financeAcc.DreamUFinanceAccVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Dream finance acc controller.
 */
@RestController
@RequestMapping("/dream/financeAcc")
@Slf4j
public class DreamFinanceAccController {


    @Resource
    private DreamUFinanceAccService dreamUFinanceAccService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 资金统计记录
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = "资金统计记录查询", moduleName = "FinanceAcc", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<DreamUFinanceAccVO>> getBannerPage(@Valid DreamUFinanceAccReqVo pageVO) {
        PageResult<DreamUFinanceAccDO> pageResult = dreamUFinanceAccService.pageByParams(pageVO);
        PageResult<DreamUFinanceAccVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), DreamUFinanceAccVO.class));
        return success(empty);
    }

}
