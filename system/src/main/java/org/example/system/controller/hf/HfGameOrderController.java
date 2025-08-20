package org.example.system.controller.hf;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.hf.HfGameOrderDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGameOrderService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.hf.gameOrder.HfGameOrderReqVo;
import org.example.vo.hf.gameOrder.HfGameOrderVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Hf game order controller.
 */
@RestController
@RequestMapping("/hf/gameOrder")
@Slf4j
public class HfGameOrderController {

    @Resource
    private HfGameOrderService hfGameOrderService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 投注订单记录获取
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = " 投注订单记录获取 分页查询", moduleName = "GameOrder", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<HfGameOrderVO>> getBannerPage(@Valid HfGameOrderReqVo pageVO) {
        PageResult<HfGameOrderDO> pageResult = hfGameOrderService.pageByParams(pageVO);
        PageResult<HfGameOrderVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), HfGameOrderVO.class));
        return success(empty);
    }


}
