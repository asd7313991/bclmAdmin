package org.example.controller;

import ma.glasnost.orika.MapperFacade;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.annotations.OperateLog;
import org.example.po.system.DictDataDO;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.service.system.dict.DictDataService;
import org.example.vo.system.dict.vo.data.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;

@RestController
@RequestMapping("/system/dict-data")
@Validated
public class DictDataController {

    @Resource
    private DictDataService dictDataService;
    @Resource
    private MapperFacade mapperFacade;

    @PostMapping("/create")
//    @SystemCheckPermission(value = "system:dict:create")
//    @TLogAspectExt(str = "新增字典数据",moduleName = "Dict",type = OperateTypeEnum.CREATE,convert = TLogConvert.class)
    public CommonResult<Long> createDictData(@Valid @RequestBody DictDataCreateReqVO reqVO) {
        Long dictDataId = dictDataService.createDictData(reqVO);
        return success(dictDataId);
    }

    @PutMapping("/update")
//    @Operation(summary = "修改字典数据")
//    @SystemCheckPermission(value = "system:dict:update")
//    @TLogAspectExt(str = "修改字典数据",moduleName = "Dict",type = OperateTypeEnum.UPDATE,convert = TLogConvert.class)
    public CommonResult<Boolean> updateDictData(@Valid @RequestBody DictDataUpdateReqVO reqVO) {
        dictDataService.updateDictData(reqVO);
        return success(true);
    }

    @PostMapping("/delete")
//    @Operation(summary = "删除字典数据")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @SystemCheckPermission(value = "system:dict:delete")
//    @TLogAspectExt(str = "删除字典数据",moduleName = "Dict",type = OperateTypeEnum.DELETE,convert = TLogConvert.class)
    public CommonResult<Boolean> deleteDictData(Long id) {
        dictDataService.deleteDictData(id);
        return success(true);
    }

    @GetMapping("/list-all-simple")
//    @Operation(summary = "获得全部字典数据列表", description = "一般用于管理后台缓存字典数据在本地")
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<DictDataSimpleRespVO>> getSimpleDictDataList() {
        List<DictDataDO> list = dictDataService.getDictDataList();
        return success(mapperFacade.mapAsList(list, DictDataSimpleRespVO.class));
    }

    @GetMapping("/page")
//    @Operation(summary = "/获得字典类型的分页列表")
//    @SystemCheckPermission(value = "system:dict:query")
//    @TLogAspectExt(str = "获得字典类型的分页列表",moduleName = "Dict",type = OperateTypeEnum.GET,convert = TLogConvert.class)
    public CommonResult<PageResult<DictDataRespVO>> getDictTypePage(@Valid DictDataPageReqVO reqVO) {
        PageResult<DictDataDO> dictDataPage = dictDataService.getDictDataPage(reqVO);
        PageResult<DictDataRespVO> empty = PageResult.empty(dictDataPage.getTotal());
        empty.setList(mapperFacade.mapAsList(dictDataPage.getList(), DictDataRespVO.class));
        return success(empty);
    }

    @GetMapping(value = "/get")
//    @Operation(summary = "/查询字典数据详细")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @SystemCheckPermission(value = "system:dict:query")
//    @TLogAspectExt(str = "查询字典数据详细",moduleName = "Dict",type = OperateTypeEnum.GET,convert = TLogConvert.class)
    public CommonResult<DictDataRespVO> getDictData(@RequestParam("id") Long id) {
        return success(mapperFacade.map(dictDataService.getDictData(id), DictDataRespVO.class));
    }

    @GetMapping("/export")
//    @Operation(summary = "导出字典数据")
//    @SystemCheckPermission(value = "system:dict:export")
    @OperateLog(type = EXPORT)
//    @TLogAspectExt(str = "查询字典数据详细",moduleName = "Dict",type = EXPORT)
    public void export(HttpServletResponse response, @Valid DictDataExportReqVO reqVO) throws IOException {
        List<DictDataDO> list = dictDataService.getDictDataList(reqVO);
        List<DictDataExcelVO> data = mapperFacade.mapAsList(list, DictDataExcelVO.class);
        // 输出
        ExcelUtils.write(response, "字典数据.xls", "数据列表", DictDataExcelVO.class, data);
    }

}
