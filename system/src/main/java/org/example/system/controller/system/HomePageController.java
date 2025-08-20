package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.service.system.homepage.HomePagePerDayVO;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.service.system.homepage.HomePageVO;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.system.homepage.HomePageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * The type Home page controller.
 */
@Tag(name = "首页")
@RestController
@RequestMapping("/system/home-page")
@Validated
public class HomePageController {

    @Resource
    private HomePageService homePageService;

    /**
     * Ge total common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @GetMapping("/get-total")
//    @Operation(summary = "首页统计接口")
    @TLogAspectExt(str = "首页统计接口", moduleName = "HomeIndex", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
//    @SystemCheckPermission(value = "system:home-page:get-total")
    public CommonResult<HomePageVO> geTotal(@Valid HomePageReqVO reqVO) {
        HomePageVO vo = homePageService.geTotal(reqVO);
        return CommonResult.success(vo);
    }

    /**
     * Gets per day list.
     *
     * @param reqVO the req vo
     * @return the per day list
     */
    @GetMapping("/get-per-day")
//    @Operation(summary = "首页统计每天")
    @TLogAspectExt(str = "首页统计每天", moduleName = "HomeIndex", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
//    @SystemCheckPermission(value = "system:home-page:get-per-day")
    public CommonResult<List<HomePagePerDayVO>> getPerDayList(@Valid HomePageReqVO reqVO) {
        List<HomePagePerDayVO> list = homePageService.getPerDayList(reqVO);
        return CommonResult.success(list);
    }

}
