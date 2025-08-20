package org.example.system.controller.system;

import cn.hutool.core.util.StrUtil;
import com.xingyuv.captcha.model.common.ResponseModel;
import com.xingyuv.captcha.model.vo.CaptchaVO;
import com.xingyuv.captcha.service.CaptchaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.system.config.log.SystemTLogConvert;
import org.example.util.servlet.ServletUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码
 *
 * @author 后台源码
 */
@Tag(name = "管理后台 - 验证码")
@RestController("adminCaptchaController")
@RequestMapping("/system/captcha")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * Get response model.
     *
     * @param data    the data
     * @param request the request
     * @return the response model
     */
    @PostMapping({"/get"})
//    @Operation(summary = "获得验证码")
//    @PermitAll
//    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TLogAspectExt(str = "获得验证码", moduleName = "Captcha", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public ResponseModel get(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.get(data);
    }

    /**
     * Check response model.
     *
     * @param data    the data
     * @param request the request
     * @return the response model
     */
    @PostMapping("/check")
//    @Operation(summary = "校验验证码")
//    @PermitAll
//    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    @TLogAspectExt(str = "获得验证码", moduleName = "Captcha", type = OperateTypeEnum.OTHER, convert = SystemTLogConvert.class)
    public ResponseModel check(@RequestBody CaptchaVO data, HttpServletRequest request) {
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.check(data);
    }

    /**
     * Gets remote id.
     *
     * @param request the request
     * @return the remote id
     */
    public static String getRemoteId(HttpServletRequest request) {
        String ip = ServletUtils.getClientIP(request);
        String ua = request.getHeader("user-agent");
        if (StrUtil.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }

}
