package org.example.system.controller.system;

import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.dept.DeptConvert;
import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.system.dept.DeptService;
import org.example.system.vo.dept.vo.dept.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

import static org.example.pojo.CommonResult.success;

/**
 * 管理后台 - 部门
 */
@RestController
@RequestMapping("/system/dept")
@Validated
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * Create dept common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("create")
//    @Operation(summary = "创建部门")
    @TLogAspectExt(str = "创建部门", moduleName = "Dept", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createDept(@Valid @RequestBody DeptCreateReqVO reqVO) {
        Long deptId = deptService.createDept(reqVO);
        return success(deptId);
    }

    /**
     * Update dept common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("update")
    @SystemCheckPermission(value = "system:dept:update")
    @TLogAspectExt(str = "更新部门", moduleName = "Dept", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateDept(@Valid @RequestBody DeptUpdateReqVO reqVO) {
        deptService.updateDept(reqVO);
        return success(true);
    }

    /**
     * Delete dept common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("delete")
    @SystemCheckPermission(value = "system:dept:delete")
    @TLogAspectExt(str = "删除部门", moduleName = "Dept", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteDept(@RequestParam("id") Long id) {
        deptService.deleteDept(id);
        return success(true);
    }

    /**
     * Gets dept list.
     *
     * @param reqVO the req vo
     * @return the dept list
     */
    @GetMapping("/list")
//    @Operation(summary = "获取部门列表")
    @SystemCheckPermission(value = "system:dept:query")
    @TLogAspectExt(str = "获取部门列表", moduleName = "Dept", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<DeptRespVO>> getDeptList(DeptListReqVO reqVO) {
        List<DeptDO> list = deptService.getDeptList(reqVO);
        list.sort(Comparator.comparing(DeptDO::getSort));
        return success(DeptConvert.INSTANCE.convertList(list));
    }

    /**
     * 获取部门精简信息列表
     * 只包含被开启的部门，主要用于前端的下拉选项
     *
     * @return simple dept list
     */
    @GetMapping("/list-all-simple")
    @TLogAspectExt(str = "获取部门精简信息列表", moduleName = "Dept", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<DeptSimpleRespVO>> getSimpleDeptList() {
        // 获得部门列表，只要开启状态的
        DeptListReqVO reqVO = new DeptListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<DeptDO> list = deptService.getDeptList(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(DeptDO::getSort));
        return success(DeptConvert.INSTANCE.convertList02(list));
    }

    /**
     * Gets dept.
     *
     * @param id the id
     * @return the dept
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:dept:query")
    @TLogAspectExt(str = "获得部门信息", moduleName = "Dept", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<DeptRespVO> getDept(@RequestParam("id") Long id) {
        return success(DeptConvert.INSTANCE.convert(deptService.getDept(id)));
    }

}
