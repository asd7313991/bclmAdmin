package org.example.player.controller;


import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.common.PlayerErrorCodeConstants;
import org.example.player.config.log.PlayerLogConvert;
import org.example.player.config.sa.StpPlayerUtil;
import org.example.player.util.TokenUtil;
import org.example.player.vo.AuthReqVO;
import org.example.player.vo.DreamUUserSimpleVO;
import org.example.po.dream.dream.DreamUUserDO;
import org.example.pojo.CommonResult;
import org.example.service.dream.DreamUUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.example.exception.util.ServiceExceptionUtil.exception;

@RestController
@RequestMapping("/player/user")
//@Validated
@Slf4j
public class PlayerAuthController {

    @Resource
    private DreamUUserService dreamUUserService;
    @Resource
    private MapperFacade mapperFacade;


    /**
     * 登录
     *
     * @param authReqVO
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @TLogAspectExt(str = "登录", moduleName = "Auth", type = OperateTypeEnum.OTHER, convert = PlayerLogConvert.class)
    @PostMapping(value = "/login")
    public CommonResult<DreamUUserSimpleVO> login(@RequestBody AuthReqVO authReqVO, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        DreamUUserDO dreamUUserDO = null;
        try {
            dreamUUserDO = dreamUUserService.selectByUserName(authReqVO.getUname());
            if (dreamUUserDO == null) {
                throw exception(PlayerErrorCodeConstants.USER_NOT_FOUND);
            }

            // 验证密码是否正确
            if (!BCrypt.hashpw(authReqVO.getPassword(), dreamUUserDO.getPassSalt()).equals(dreamUUserDO.getUserPass())) {
                throw exception(GlobalErrorCodeConstants.PASSWORD_IS_ERROR);
            }
            dreamUUserDO.setLoginIp(ServletUtil.getClientIP(httpServletRequest, "Ip"));

//        用户状态0正常，1删除，2禁止登录,3禁止交易
            if (Lists.newArrayList(1, 2, 4).contains(dreamUUserDO.getStatus())) {
                log.warn("用户登录状态异常,请联系客服");
                throw exception(GlobalErrorCodeConstants.USER_STATUS_IS_WARN);
            }

            if (dreamUUserDO.getLoginErrorNum() > 4 && DateUtil.between(dreamUUserDO.getLastLoginTime(), DateUtil.parseDate(DateUtil.now()), DateUnit.MINUTE) < 5) {
                log.warn("用户登录错误次数超过5次,禁止登陆5分钟,请稍后重试");
                throw exception(GlobalErrorCodeConstants.USER_STATUS_IS_WARN);
            }

            // 用户登陆
            StpPlayerUtil.login(dreamUUserDO.getId(), SaLoginModel.create().setIsLastingCookie(Boolean.TRUE));
            //用户登陆后，如果用户禁止交易，设置禁止交易时间
            if (dreamUUserDO.getStatus() == 3) {
                // 禁用30天
                StpPlayerUtil.disable(dreamUUserDO.getId(), "finance", 86400 * 30);
            }

            // 游戏API的token
            TokenUtil.generateToken(dreamUUserDO.getId().intValue(), httpServletRequest, httpServletResponse);
            DreamUUserSimpleVO dreamUUserSimpleVO = mapperFacade.map(dreamUUserDO, DreamUUserSimpleVO.class);
            dreamUUserSimpleVO.setTokenName(StpPlayerUtil.getTokenName());
            dreamUUserSimpleVO.setTokenValue(StpPlayerUtil.getTokenValue());
            dreamUUserService.loginChain(dreamUUserDO, Boolean.TRUE, null);
            return CommonResult.success(dreamUUserSimpleVO);
        } catch (Exception exception) {
            dreamUUserService.loginChain(dreamUUserDO, Boolean.FALSE, exception);
            throw exception;
        }
    }


    /**
     * 退出登录
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @TLogAspectExt(str = "退出登录", moduleName = "Auth", type = OperateTypeEnum.OTHER, convert = PlayerLogConvert.class)
    @PostMapping(value = "/logout")
    public CommonResult<Boolean> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("请求登录");

        try {
            StpPlayerUtil.logout(StpPlayerUtil.getLoginIdAsLong());
        } catch (Exception exception) {
            log.error("退出登录失败", exception);
        }
        // 游戏API的token
        return CommonResult.success(Boolean.TRUE);
    }


    /**
     * 获取验证码
     *
     * @param authReqVO
     * @return
     */
    @TLogAspectExt(str = "获取验证码", moduleName = "Auth", type = OperateTypeEnum.OTHER, convert = PlayerLogConvert.class)
    @PostMapping("getCaptchaCode")
    public CommonResult getCaptchaCode(@RequestBody AuthReqVO authReqVO) {

        switch (authReqVO.getType()) {
            case 1:
                // 注册获取验证码
                dreamUUserService.getCaptchaNoLoginByUserName(1, authReqVO.getUname());
                break;
            case 2:
                // 登录获取验证码
                dreamUUserService.getCaptchaNoLoginByUserName(2, authReqVO.getUname());
                break;
            case 3:
                // 交易获取验证码
                dreamUUserService.getCaptchaNeedLoginByUserId(3, StpPlayerUtil.getLoginIdAsLong());
                break;
            case 4:
                // 登录后修改密码获取验证码
                dreamUUserService.getCaptchaNeedLoginByUserId(4, StpPlayerUtil.getLoginIdAsLong());
                break;
            default:
                log.info("获取验证码，未指定场景值");
                throw exception(PlayerErrorCodeConstants.PARAMS_IS_UNDEFINED);
        }

        return CommonResult.success(Boolean.TRUE);
    }

    /**
     * 手动前置验证验证码
     *
     * @param authReqVO
     * @return
     */
    @PostMapping("checkCaptchaCode")
    public CommonResult checkCaptchaCode(@RequestBody AuthReqVO authReqVO) {

        return CommonResult.success(Boolean.TRUE);
    }


    /**
     * 注册
     *
     * @param authReqVO
     * @return
     */
    @TLogAspectExt(str = "注册", moduleName = "Auth", type = OperateTypeEnum.OTHER, convert = PlayerLogConvert.class)
    @PostMapping("register")
    public CommonResult register(@RequestBody AuthReqVO authReqVO, HttpServletRequest httpServletRequest) {
        DreamUUserDO dreamUUserDO = new DreamUUserDO();
        dreamUUserDO.setUserName(authReqVO.getUname());
        dreamUUserDO.setUserPass(authReqVO.getPassword());
        dreamUUserDO.setUserType(0);
        //注册IP地址
        dreamUUserDO.setLoginIp(ServletUtil.getClientIP(httpServletRequest, "Ip"));
        Boolean register = dreamUUserService.register(dreamUUserDO);
        return CommonResult.success(register);
    }


}
