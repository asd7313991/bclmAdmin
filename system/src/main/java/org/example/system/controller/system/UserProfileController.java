package org.example.system.controller.system;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.config.satoken.StpSystemUtil;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemStpInterface;
import org.example.system.convert.user.UserConvert;
import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.db.mysql.po.system.DeptDO;
import org.example.system.db.mysql.po.system.PostDO;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.system.dept.DeptService;
import org.example.system.system.dept.PostService;
import org.example.system.system.permission.PermissionService;
import org.example.system.system.permission.RoleService;
import org.example.system.system.user.AdminUserService;
import org.example.system.vo.user.vo.profile.UserProfileRespVO;
import org.example.system.vo.user.vo.profile.UserProfileUpdatePasswordReqVO;
import org.example.system.vo.user.vo.profile.UserProfileUpdateReqVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static org.example.pojo.CommonResult.success;


/**
 * The type User profile controller.
 */
@Tag(name = "管理后台 - 用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class UserProfileController {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private SystemStpInterface systemStpInterface;

    /**
     * Profile common result.
     *
     * @return the common result
     */
    @GetMapping("/get")
    @TLogAspectExt(str = "获得登录用户信息", moduleName = "admin-user-profile", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<UserProfileRespVO> profile() {
        // 获得用户基本信息
        AdminUserDO user = userService.getUser(StpSystemUtil.getLoginIdAsLong());
        UserProfileRespVO resp = mapperFacade.map(user, UserProfileRespVO.class);
        // 获得用户角色
        List<RoleDO> userRoles = null;
        resp.setRoles(mapperFacade.mapAsList(systemStpInterface.getRoleVoLst(StpSystemUtil.getLoginId(), StpSystemUtil.TYPE), UserProfileRespVO.Role.class));
        // 获得部门信息
        if (user.getDeptId() != null) {
            DeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(UserConvert.INSTANCE.convert02(dept));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<PostDO> posts = postService.getPostList(user.getPostIds());
            resp.setPosts(UserConvert.INSTANCE.convertList02(posts));
        }
        // 获得社交用户信息
//        List<SocialUserDO> socialUsers = socialService.getSocialUserList(user.getId(), UserTypeEnum.ADMIN.getValue());
//        resp.setSocialUsers(UserConvert.INSTANCE.convertList03(socialUsers));
        return success(resp);
    }

    /**
     * Update user profile common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update")
    @TLogAspectExt(str = "修改用户个人信息", moduleName = "admin-user-profile", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody UserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(StpSystemUtil.getLoginIdAsLong(), reqVO);
        return success(true);
    }

    /**
     * Update user profile password common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update-password")
    @TLogAspectExt(str = "修改用户个人密码", moduleName = "admin-user-profile", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody UserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(StpSystemUtil.getLoginIdAsLong(), reqVO);
        return success(true);
    }

    /**
     * Update user avatar common result.
     *
     * @param file the file
     * @return the common result
     * @throws Exception the exception
     */
    @RequestMapping(value = "/update-avatar", method = {RequestMethod.POST, RequestMethod.PUT})
    // 解决 uni-app 不支持 Put 上传文件的问题
    @TLogAspectExt(str = "上传用户个人头像", moduleName = "admin-user-profile", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<String> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
//            throw exception(null);
        }
        String avatar = userService.updateUserAvatar(StpSystemUtil.getLoginIdAsLong(), file.getInputStream());
        return success(avatar);
    }

}
