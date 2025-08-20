package org.example.controller;

import ma.glasnost.orika.MapperFacade;
import org.example.excel.core.util.ExcelUtils;
import org.example.po.system.DictTypeDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.system.dict.DictTypeService;
import org.example.vo.system.dict.vo.type.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.example.pojo.CommonResult.success;


//@Tag(name = "管理后台 - 字典类型")
@RestController
@RequestMapping("/system/dict-type")
@Validated
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private MapperFacade mapperFacade;

    @PostMapping("/create")
//    @Operation(summary = "创建字典类型")
//    @SystemCheckPermission(value = "system:dict:create")
//    @TLogAspectExt(str = "查询字典数据详细",moduleName = "Dict",type = OperateTypeEnum.CREATE,convert = TLogConvert.class)
    public CommonResult<Long> createDictType(@Valid @RequestBody DictTypeCreateReqVO reqVO) {
        Long dictTypeId = dictTypeService.createDictType(reqVO);
        return success(dictTypeId);
    }

    @PutMapping("/update")
//    @Operation(summary = "修改字典类型")
//    @SystemCheckPermission(value = "system:dict:update")
//    @TLogAspectExt(str = "修改字典类型",moduleName = "Dict",type = OperateTypeEnum.UPDATE,convert = TLogConvert.class)
    public CommonResult<Boolean> updateDictType(@Valid @RequestBody DictTypeUpdateReqVO reqVO) {
        dictTypeService.updateDictType(reqVO);
        return success(true);
    }

    @PostMapping("/delete")
//    @SystemCheckPermission(value = "system:dict:delete")
//    @TLogAspectExt(str = "删除字典类型",moduleName = "Dict",type = OperateTypeEnum.DELETE,convert = TLogConvert.class)
    public CommonResult<Boolean> deleteDictType(Long id) {
        dictTypeService.deleteDictType(id);
        return success(true);
    }

    @GetMapping("/page")
//    @SystemCheckPermission(value = "system:dict:query")
//    @TLogAspectExt(str = "获得字典类型的分页列表",moduleName = "Dict",type = OperateTypeEnum.GET,convert = TLogConvert.class)
    public CommonResult<PageResult<DictTypeRespVO>> pageDictTypes(@Valid DictTypePageReqVO reqVO) {
        PageResult<DictTypeDO> dictTypePage = dictTypeService.getDictTypePage(reqVO);
        PageResult<DictTypeRespVO> empty = PageResult.empty(dictTypePage.getTotal());
        empty.setList(mapperFacade.mapAsList(dictTypePage.getList(), DictTypeRespVO.class));
        return success(empty);
    }

    @GetMapping(value = "/get")
//    @SystemCheckPermission(value = "system:dict:query")
//    @TLogAspectExt(str = "查询字典类型详细",moduleName = "Dict",type = OperateTypeEnum.GET,convert = TLogConvert.class)
    public CommonResult<DictTypeRespVO> getDictType(@RequestParam("id") Long id) {
        return success(mapperFacade.map(dictTypeService.getDictType(id), DictTypeRespVO.class));
    }

    @GetMapping("/list-all-simple")
//    @TLogAspectExt(str = "获得全部字典类型列表",moduleName = "Dict",type = OperateTypeEnum.GET,convert = TLogConvert.class)
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<DictTypeSimpleRespVO>> getSimpleDictTypeList() {
        List<DictTypeDO> list = dictTypeService.getDictTypeList();
        return success(mapperFacade.mapAsList(list, DictTypeSimpleRespVO.class));
    }

    @GetMapping("/export")
//    @TLogAspectExt(str = "导出数据类型",moduleName = "Dict",type = EXPORT)
    public void export(HttpServletResponse response, @Valid DictTypeExportReqVO reqVO) throws IOException {
        List<DictTypeDO> list = dictTypeService.getDictTypeList(reqVO);
        List<DictTypeExcelVO> data = mapperFacade.mapAsList(list, DictTypeExcelVO.class);
        // 输出
        ExcelUtils.write(response, "字典类型.xls", "类型列表", DictTypeExcelVO.class, data);
    }

}
