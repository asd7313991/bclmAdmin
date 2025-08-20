package org.example.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.enums.SexEnum;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.user.UserConvert;
import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.system.dept.DeptService;
import org.example.system.system.user.AdminUserService;
import org.example.system.vo.user.vo.user.*;
import org.example.util.collection.MapUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;
import static org.example.util.collection.CollectionUtils.convertList;
import static org.example.util.collection.CollectionUtils.convertSet;

/**
 * The type User controller.
 */
@Slf4j
@Tag(name = "管理后台 - 用户")
@RestController
@RequestMapping("/system/user")
@Validated
public class UserController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private MapperFacade mapperFacade;

    /**
     * Create user common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:user:create")
    @TLogAspectExt(str = "新增用户", moduleName = "admin-user", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createUser(@Valid @RequestBody UserCreateReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return success(id);
    }

    /**
     * Update user common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("update")
    @Operation(summary = "修改用户")
    @SystemCheckPermission(value = "system:user:update")
    @TLogAspectExt(str = "修改用户", moduleName = "admin-user", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateUser(@Valid @RequestBody UserUpdateReqVO reqVO) {
        userService.updateUser(reqVO);
        return success(true);
    }

    /**
     * Delete user common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @SystemCheckPermission(value = "system:user:delete")
    @TLogAspectExt(str = "删除用户", moduleName = "admin-user", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return success(true);
    }

    /**
     * Update user password common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update-password")
    @Operation(summary = "重置用户密码")
    @SystemCheckPermission(value = "system:user:update-password")
    @TLogAspectExt(str = "重置用户密码", moduleName = "admin-user", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody UserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO.getId(), reqVO.getPassword());
        return success(true);
    }

    /**
     * Update user status common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update-status")
    @Operation(summary = "修改用户状态")
    @SaCheckPermission("system:user:update")
    @TLogAspectExt(str = "修改用户状态", moduleName = "admin-user", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateUserStatus(@Valid @RequestBody UserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    /**
     * Gets user page.
     *
     * @param reqVO the req vo
     * @return the user page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:user:list")
    @TLogAspectExt(str = "修改用户状态", moduleName = "admin-user", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<UserPageItemRespVO>> getUserPage(@Valid UserPageReqVO reqVO) {
        // 获得用户分页列表
        PageResult<AdminUserDO> pageResult = userService.getUserPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal())); // 返回空
        }

        // 获得拼接需要的数据
        Collection<Long> deptIds = convertList(pageResult.getList(), AdminUserDO::getDeptId);
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(deptIds);
        // 拼接结果返回
        List<UserPageItemRespVO> userList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(user -> {
            UserPageItemRespVO respVO = UserConvert.INSTANCE.convert(user);
            respVO.setDept(UserConvert.INSTANCE.convert(deptMap.get(user.getDeptId())));
            userList.add(respVO);
        });
        return success(new PageResult<>(userList, pageResult.getTotal()));
    }

    /**
     * 获取用户精简信息列表
     *
     * @return simple user list
     * @description 只包含被开启的用户 ，主要用于前端的下拉选项
     */
    @GetMapping("/list-all-simple")
    @SystemCheckPermission(value = "system:user:list")
    @TLogAspectExt(str = "修改用户状态", moduleName = "admin-user", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<List<UserSimpleRespVO>> getSimpleUserList() {
        // 获用户列表，只要开启状态的
        List<AdminUserDO> list = userService.getUserListByStatus(CommonStatusEnum.ENABLE.getStatus());
        // 排序后，返回给前端
        return success(mapperFacade.mapAsList(list, UserSimpleRespVO.class));
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:user:query")
    @TLogAspectExt(str = "修改用户状态", moduleName = "admin-user", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<UserRespVO> getUser(@RequestParam("id") Long id) {
        AdminUserDO user = userService.getUser(id);
        // 获得部门数据
        DeptDO dept = deptService.getDept(user.getDeptId());
        UserPageItemRespVO convert = UserConvert.INSTANCE.convert(user);
        convert.setDept(UserConvert.INSTANCE.convert(dept));
        return success(convert);
    }

    /**
     * Export user list.
     *
     * @param reqVO    the req vo
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping("/export")
    @SystemCheckPermission(value = "system:user:export")
    @TLogAspectExt(str = "导出用户", moduleName = "admin-user", type = EXPORT)
    public void exportUserList(@Validated UserExportReqVO reqVO,
                               HttpServletResponse response) throws IOException {
        // 获得用户列表
        List<AdminUserDO> users = userService.getUserList(reqVO);

        // 获得拼接需要的数据
        Collection<Long> deptIds = convertList(users, AdminUserDO::getDeptId);
        Map<Long, DeptDO> deptMap = deptService.getDeptMap(deptIds);
        Map<Long, AdminUserDO> deptLeaderUserMap = userService.getUserMap(
                convertSet(deptMap.values(), DeptDO::getLeaderUserId));
        // 拼接数据
        List<UserExcelVO> excelUsers = new ArrayList<>(users.size());
        users.forEach(user -> {
            UserExcelVO excelVO = UserConvert.INSTANCE.convert02(user);
            // 设置部门
            MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> {
                excelVO.setDeptName(dept.getName());
                // 设置部门负责人的名字
                MapUtils.findAndThen(deptLeaderUserMap, dept.getLeaderUserId(),
                        deptLeaderUser -> excelVO.setDeptLeaderNickname(deptLeaderUser.getNickname()));
            });
            excelUsers.add(excelVO);
        });

        // 输出
        ExcelUtils.write(response, "用户数据.xls", "用户列表", UserExcelVO.class, excelUsers);
    }

    /**
     * Import template.
     *
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入用户模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<UserImportExcelVO> list = Arrays.asList(
                UserImportExcelVO.builder().username("test001").deptId(1L).email("123456@google.cn").mobile("15512344321")
                        .nickname("后台").status(CommonStatusEnum.ENABLE.getStatus()).sex(SexEnum.MALE.getSex()).build(),
                UserImportExcelVO.builder().username("test001").deptId(2L).email("123456@google.cn").mobile("15512344321")
                        .nickname("后台").status(CommonStatusEnum.DISABLE.getStatus()).sex(SexEnum.FEMALE.getSex()).build()
        );

        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", UserImportExcelVO.class, list);
    }

    /**
     * Import excel common result.
     *
     * @param file          the file
     * @param updateSupport the update support
     * @return the common result
     * @throws Exception the exception
     */
    @PostMapping("/import")
    @Operation(summary = "导入用户")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @SystemCheckPermission(value = "system:user:import")
    public CommonResult<UserImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<UserImportExcelVO> list = ExcelUtils.read(file, UserImportExcelVO.class);
        return success(userService.importUserList(list, updateSupport));
    }

}
