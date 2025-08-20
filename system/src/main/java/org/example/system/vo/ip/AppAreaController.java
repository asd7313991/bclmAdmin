//package org.example.vo.ip;
//
//import cn.hutool.core.lang.Assert;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.example.convert.ip.AreaConvert;
//import org.example.enums.Area;
//import org.example.pojo.CommonResult;
//import org.example.util.AreaUtils;
//import org.example.vo.ip.vo.AppAreaNodeRespVO;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//import static org.example.pojo.CommonResult.success;
//
//
//@Tag(name = "用户 App - 地区")
//@RestController
//@RequestMapping("/system/area")
//@Validated
//public class AppAreaController {
//
////    @GetMapping("/tree")
////    @Operation(summary = "获得地区树")
////    public CommonResult<List<AppAreaNodeRespVO>> getAreaTree() {
////        Area area = AreaUtils.getArea(Area.ID_CHINA);
////        Assert.notNull(area, "获取不到中国");
////        return success(AreaConvert.INSTANCE.convertList3(area.getChildren()));
////    }
//
//}
