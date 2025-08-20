package org.example.system.controller.system;


import io.swagger.v3.oas.annotations.tags.Tag;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.db.mysql.po.system.BannerDO;
import org.example.system.system.banner.BannerService;
import org.example.system.vo.banner.BannerCreateReqVO;
import org.example.system.vo.banner.BannerPageReqVO;
import org.example.system.vo.banner.BannerRespVO;
import org.example.system.vo.banner.BannerUpdateReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.example.pojo.CommonResult.success;


/**
 * The type Banner controller.
 */
@Tag(name = "管理后台 - Banner 管理")
@RestController
@RequestMapping("/system/banner")
@Validated
public class BannerController {

    @Resource
    private BannerService bannerService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Create banner common result.
     *
     * @param createReqVO the create req vo
     * @return the common result
     */
    @PostMapping("/create")
//    @Operation(summary = "创建 Banner")
    @SystemCheckPermission(value = "market:banner:create")
    @TLogAspectExt(str = "创建 Banner", moduleName = "Banner", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createBanner(@Valid @RequestBody BannerCreateReqVO createReqVO) {
        return success(bannerService.createBanner(createReqVO));
    }

    /**
     * Update banner common result.
     *
     * @param updateReqVO the update req vo
     * @return the common result
     */
    @PutMapping("/update")
//    @Operation(summary = "更新 Banner")
    @SystemCheckPermission(value = "market:banner:update")
    @TLogAspectExt(str = "更新 Banner", moduleName = "Banner", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateBanner(@Valid @RequestBody BannerUpdateReqVO updateReqVO) {
        bannerService.updateBanner(updateReqVO);
        return success(true);
    }

    /**
     * Delete banner common result.
     *
     * @param id the id
     * @return the common result
     */
    @DeleteMapping("/delete")
//    @Operation(summary = "删除 Banner")
//    @Parameter(name = "id", description = "编号", required = true)
    @SystemCheckPermission(value = "market:banner:delete")
    @TLogAspectExt(str = "删除 Banner", moduleName = "Banner", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteBanner(@RequestParam("id") Long id) {
        bannerService.deleteBanner(id);
        return success(true);
    }

    /**
     * Gets banner.
     *
     * @param id the id
     * @return the banner
     */
    @GetMapping("/get")
//    @Operation(summary = "获得 Banner")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @SystemCheckPermission(value = "market:banner:query")
    @TLogAspectExt(str = "获得 Banner", moduleName = "Banner", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<BannerRespVO> getBanner(@RequestParam("id") Long id) {
        BannerDO banner = bannerService.getBanner(id);
        return success(mapperFacade.map(banner, BannerRespVO.class));
    }

    /**
     * Gets banner page.
     *
     * @param pageVO the page vo
     * @return the banner page
     */
    @GetMapping("/page")
//    @Operation(summary = "获得 Banner 分页")
    @SystemCheckPermission(value = "market:banner:query")
    @TLogAspectExt(str = "获得 Banner 分页", moduleName = "Banner", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<BannerRespVO>> getBannerPage(@Valid BannerPageReqVO pageVO) {
        PageResult<BannerDO> pageResult = bannerService.getBannerPage(pageVO);
        PageResult<BannerRespVO> empty = PageResult.empty(pageResult.getTotal());
        empty.setList(mapperFacade.mapAsList(pageResult.getList(), BannerRespVO.class));
        return success(empty);
    }

}
