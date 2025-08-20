package org.example.system.config.satoken;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.config.satoken.StpSystemUtil;
import org.example.pojo.CommonResult;
import org.example.util.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.example.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * The type Sa system token config.
 */
@Slf4j
@Configuration
public class SaSystemTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 [Sa-Token 全局过滤器]
     *
     * @return the sa servlet filter
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()

                // 指定 [拦截路由] 与 [放行路由]
                .addInclude("/**").addExclude("/favicon.ico","/system/captcha/*", "/system/auth/login","/system/auth/logout","/system/file/uploadFile","/system/file/uploadFileByMultipartFile")
                // 认证函数: 每次请求执行
                .setAuth(obj -> {
                    SaManager.getLog().debug("----- 请求path={}  提交token={}", SaHolder.getRequest().getRequestPath(), StpSystemUtil.getTokenValue());
                    SaRouter.match("/**").notMatch("*.html", "*.js", "*.css").check(r -> {
                        String ipAddr = IPUtils.getIpAddr(null);
                        if (StpSystemUtil.isLogin()){
                            log.info("{},登录后获取到IP为：{}", SaHolder.getRequest().getRequestPath(),ipAddr);
                        }else {
                            log.info("{},未登录获取到IP为：{}", SaHolder.getRequest().getRequestPath(),ipAddr);
                        }
                    });
                    SaRouter.match("/system/**", "/system/captcha/**", r -> {
                        StpSystemUtil.checkLogin();
                    });
                    SaRouter.match("/dream/**", "", r -> {
                        StpSystemUtil.checkLogin();
                    });
                    SaRouter.match("/hf/**", "", r -> {
                        StpSystemUtil.checkLogin();
                    });
                })

                // 异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    return JSONUtil.toJsonStr(CommonResult.error(UNAUTHORIZED));
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {
                    SaHolder.getResponse()
                            // 允许第三方 Cookie
                            .setHeader("Access-Control-Allow-Credentials", "true")

                            // ---------- 设置跨域响应头 ----------
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "*")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                    ;

                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> {
                            })
                            .back();
                })
                ;
    }

    /**
     * Rewrite sa strategy.
     */
    @Autowired
    public void rewriteSaStrategy() {
        // 重写Sa-Token的注解处理器，增加注解合并功能
        SaStrategy.instance.getAnnotation = (element, annotationClass) -> {
            return AnnotatedElementUtils.getMergedAnnotation(element, annotationClass);
        };
    }


    // Sa-Token 整合 jwt (Simple 简单模式)
//    @Bean
//    public StpLogic getStpLogicJwt() {
//        return new StpLogicJwtForSimple();
//    }
}
