//package org.example.controller;
//
//
//import com.baomidou.mybatisplus.extension.service.IService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import lombok.extern.slf4j.Slf4j;
//import ma.glasnost.orika.MapperFacade;
//import org.example.config.satoken.SystemCheckPermission;
//import org.example.db.mysql.po.system.BannerDO;
//import org.example.pojo.CommonResult;
//import org.example.pojo.PageResult;
//import org.example.vo.banner.BannerCreateReqVO;
//import org.example.vo.banner.BannerPageReqVO;
//import org.example.vo.banner.BannerRespVO;
//import org.example.vo.banner.BannerUpdateReqVO;
//import org.example.vo.base.CreateReqVO;
//import org.example.vo.base.PageReqVO;
//import org.example.vo.base.UpdateReqVO;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//
//import static org.example.pojo.CommonResult.success;
//
//@Slf4j
//public abstract class BaseController<T> {
//    @Resource
//    private MapperFacade mapperFacade;
//    @Resource
//    private IService<T> iService;
//    private Class<T> tClass;
//
//    public IService<T> getiService() {
//        return iService;
//    }
//
//    @PostMapping("/create")
//    public  CommonResult<Boolean> createBanner(@Valid @RequestBody CreateReqVO createReqVO) {
//        boolean save = iService.save(mapperFacade.map(createReqVO, tClass));
//        return success(save);
//    }
//
//    @PutMapping("/update")
//    public CommonResult<Boolean> updateBanner(@Valid @RequestBody UpdateReqVO updateReqVO) {
//        iService.updateById(mapperFacade.map(updateReqVO,tClass));
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    public CommonResult<Boolean> deleteBanner(@RequestParam("id") Long id) {
//        iService.removeById(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    public CommonResult<T> getBanner(@RequestParam("id") Long id) {
//        T t = iService.getById(id);
//        return success(t);
//    }
//
//    @GetMapping("/page")
//    public CommonResult<PageResult<BannerRespVO>> getBannerPage(@Valid PageReqVO pageVO) {
//        PageResult<BannerDO> pageResult = iService.page();
//        PageResult<BannerRespVO> empty = PageResult.empty(pageResult.getTotal());
//        empty.setList(mapperFacade.mapAsList(pageResult.getList(),BannerRespVO.class));
//        return success(empty);
//    }
//
//
//
//}
