package org.example.system.controller.hf;


import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.po.dream.hf.HfGamePlanDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGamePlanService;
import org.example.system.config.log.SystemTLogConvert;
import org.example.vo.hf.gamePlan.HfGamePlanReqVo;
import org.example.vo.hf.gamePlan.HfGamePlanVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.operatelog.core.enums.OperateTypeEnum.GET;
import static org.example.pojo.CommonResult.success;

/**
 * The type Hf game plan controller.
 */
@RestController
@RequestMapping("/hf/gamePlan")
@Slf4j
public class HfGamePlanController {

    @Resource
    private HfGamePlanService hfGamePlanService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 追单计划获取
     *
     * @param pageVO the page vo
     * @return banner page
     */
    @TLogAspectExt(str = " 追单计划获取 分页查询", moduleName = "GamePlan", type = GET, convert = SystemTLogConvert.class)
    @GetMapping("/page")
    public CommonResult<PageResult<HfGamePlanVO>> getBannerPage(@Valid HfGamePlanReqVo pageVO) {
        PageResult<HfGamePlanDO> pageResult = hfGamePlanService.pageByParams(pageVO);
        PageResult<HfGamePlanVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), HfGamePlanVO.class));
        return success(empty);
    }


}
