package org.example.system.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.config.satoken.StpSystemUtil;
import org.example.enums.CommonStatusEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.db.mysql.po.system.AdminUserDO;
import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.db.mysql.po.system.RoleDO;
import org.example.system.system.permission.MenuService;
import org.example.system.system.permission.PermissionService;
import org.example.system.system.permission.RoleService;
import org.example.system.system.user.AdminUserService;
import org.example.system.vo.auth.vo.*;
import org.example.util.IPUtils;
import org.example.util.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.example.enums.ErrorCodeConstants.*;
import static org.example.exception.util.ServiceExceptionUtil.exception;
import static org.example.pojo.CommonResult.success;
import static org.example.util.collection.CollectionUtils.convertSet;


/**
 * The type Auth controller.
 */
@Tag(name = "管理后台 - 认证")
@RestController
@RequestMapping("/system/auth")
@Validated
@Slf4j
public class AuthController {

    @Resource
    private AdminUserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private MapperFacade mapperFacade;
    @Resource
    private CaptchaService captchaService;


    /**
     * Login common result.
     *
     * @param reqVO              the req vo
     * @param httpServletRequest the http servlet request
     * @return the common result
     */
    @PostMapping("/login")
    @SaIgnore
//    @PermitAll
//    @Operation(summary = "使用账号密码登录")
//    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TLogAspectExt(str = "后台登录", moduleName = "adminUser", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public CommonResult<SaTokenInfo> login(@RequestBody @Valid AuthLoginReqVO reqVO, HttpServletRequest httpServletRequest) {

        // 校验验证码
        ValidationUtils.validate(reqVO, AuthLoginReqVO.CodeEnableGroup.class);
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(reqVO.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        // 验证不通过
        if (!response.isSuccess()) {
            // 创建登录失败日志（验证码不正确)
//            createLoginLog(null, reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR, response.getRepMsg());
        }

//        获取尝试登录的所有信息
        String ipAddr = IPUtils.getIpAddr(httpServletRequest);
        log.info("ipAddr:{}", ipAddr);


        log.info("账号密码登录：{}", JSONUtil.toJsonStr(reqVO));
        AdminUserDO adminUserDO = userService.getUserByUsername(reqVO.getUsername());
        if (adminUserDO == null) {
            throw exception(USER_NOT_EXISTS);
        }
        if (!BCrypt.checkpw(reqVO.getPassword(), adminUserDO.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }


        // 登录
        StpSystemUtil.login(adminUserDO.getId(), SaLoginConfig.create());
        SaTokenInfo saTokenInfo = StpSystemUtil.getTokenInfo();
        return success(saTokenInfo);
    }

    /**
     * Logout common result.
     *
     * @param request the request
     * @return the common result
     */
    @PostMapping("/logout")
    @SaIgnore
    @TLogAspectExt(str = "登出系统", moduleName = "adminUser", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        try {
            SaSession sessionByLoginId = StpSystemUtil.getSessionByLoginId(StpSystemUtil.getLoginId());
            sessionByLoginId.logout();
            StpSystemUtil.logout(StpSystemUtil.getLoginId(), StpSystemUtil.TYPE);
        } catch (Exception exception) {
            log.warn("退出登陆失败", exception);
        }
        return success(true);
    }

    /**
     * Refresh token common result.
     *
     * @param refreshToken the refresh token
     * @return the common result
     */
    @PostMapping("/refresh-token")
    @SaIgnore
    @TLogAspectExt(str = "刷新令牌", moduleName = "adminUser", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public CommonResult<AuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(null);
    }


    /**
     * Gets permission info.
     *
     * @return the permission info
     */
    @GetMapping("/get-permission-info")
    @TLogAspectExt(str = "获取登录用户的权限信息", moduleName = "Auth", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<AuthPermissionInfoRespVO> getPermissionInfo() {
        // 1.1 获得用户信息
        Long loginIdAsLong = StpSystemUtil.getLoginIdAsLong();
        AdminUserDO user = userService.getUser(loginIdAsLong);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        AuthPermissionInfoRespVO authPermissionInfoRespVO = new AuthPermissionInfoRespVO();


        // 1.2 获得角色列表
        Set<Long> roleIds = permissionService.getUserRoleIdListByUserId(loginIdAsLong);
        List<RoleDO> roles = roleService.getRoleList(roleIds);
        roles.removeIf(role -> !CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus())); // 移除禁用的角色

        if (CollectionUtil.isEmpty(roles)) {
            log.info("无角色信息");
            throw exception(AUTH_LOGIN_USER_NO_ROLE_OR_MENU);
        }

        authPermissionInfoRespVO.setRoles(new HashSet<>(StpSystemUtil.getRoleList()));

        // 1.3 获得菜单列表
        Set<Long> menuIds = permissionService.getRoleMenuListByRoleId(convertSet(roles, RoleDO::getId));
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(menuIds)) {
            List<MenuDO> menuList = menuService.getMenuList(menuIds);
            menuList.removeIf(menu -> !CommonStatusEnum.ENABLE.getStatus().equals(menu.getStatus())); // 移除禁用的菜单
            authPermissionInfoRespVO.setMenus(permissionService.buildMenuTree(menuList));
        } else {
            log.info("无菜单信息");
            throw exception(AUTH_LOGIN_USER_NO_ROLE_OR_MENU);
        }

        authPermissionInfoRespVO.setUser(mapperFacade.map(user, AuthPermissionInfoRespVO.UserVO.class));
        authPermissionInfoRespVO.setPermissions(new HashSet<>(StpSystemUtil.getPermissionList()));
        // 2. 拼接结果返回
        return success(authPermissionInfoRespVO);
    }

    /**
     * Sms login common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
// ========== 短信登录相关 ==========
    @PostMapping("/sms-login")
    @SaIgnore
    @TLogAspectExt(str = "使用短信验证码登录", moduleName = "adminUser", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public CommonResult<AuthLoginRespVO> smsLogin(@RequestBody @Valid AuthSmsLoginReqVO reqVO) {
        return success(null);
    }

    /**
     * Send login sms code common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/send-sms-code")
    @SaIgnore
    @TLogAspectExt(str = "发送手机验证码", moduleName = "adminUser", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> sendLoginSmsCode(@RequestBody @Valid AuthSmsSendReqVO reqVO) {
//        authService.sendSmsCode(reqVO);
        return success(true);
    }

}
