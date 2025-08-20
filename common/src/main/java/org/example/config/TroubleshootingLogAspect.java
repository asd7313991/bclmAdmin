package org.example.config;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * 排错日志AOP
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TroubleshootingLogAspect {


    private final Environment environment;


    public boolean printEnable() {
        return environment.getProperty("project.log.troubleshooting.enable", Boolean.class, Boolean.FALSE);
    }


    @Pointcut("execution(* org.example..*.*(..)) && " +
            "(@within(org.springframework.web.bind.annotation.RestController) || " +
            "@within(org.springframework.stereotype.Controller) || " +
            "@within(org.springframework.stereotype.Component) || " +
            "@within(org.springframework.stereotype.Service))")
    public void logPointcut() {
    }


    @Around("logPointcut()")
    public Object print(ProceedingJoinPoint point) throws Throwable {
        String random = UUID.randomUUID().toString();
        String method = point.getSignature().getDeclaringTypeName() + "#" + point.getSignature().getName();
        long startTime = System.currentTimeMillis();


        if (printEnable()) {
            log.info("{}====TROUBLESHOOTING-LOG========= 方法 : {} \n 参数：{}",
                    random, method, Arrays.toString(point.getArgs()));
        }

        Object result = point.proceed();
        if(printEnable()) {
            long usedTime = System.currentTimeMillis() - startTime;
            log.info("{}=====TROUBLESHOOTING-LOG========= 方法 : {}，耗时:{}ms \n 返回值：{}",
                    random,method,usedTime, JSONUtil.toJsonStr(result));
        }
        return result;
    }
}
