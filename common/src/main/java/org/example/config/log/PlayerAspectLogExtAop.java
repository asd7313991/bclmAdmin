//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.config.log;

import cn.dev33.satoken.context.SaHolder;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.yomahub.tlog.context.TLogContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.config.satoken.StpPlayerUtil;
import org.example.enums.LoginResultEnum;
import org.example.enums.UserTypeEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.service.system.logger.OperateLogService;
import org.example.util.servlet.ServletUtils;
import org.example.vo.system.operatelog.OperateLogCreateReqDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static org.example.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

@Slf4j
@Component
@Aspect
public class PlayerAspectLogExtAop {

//    private ExpressRunner expressRunner = new ExpressRunner();

    public PlayerAspectLogExtAop() {
    }

    @Pointcut("@annotation(org.example.config.log.TLogAspectExt)")
    public void tLogAspectExtcut() {
    }

    @Around("tLogAspectExtcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        LocalDateTime startTime = LocalDateTime.now();
        Object[] args = jp.getArgs();
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = signature.getParameterNames();
        Map<String, Object> paramNameValueMap = MapUtil.newHashMap();

        for (int i = 0; i < parameterNames.length; ++i) {
            paramNameValueMap.put(parameterNames[i], args[i]);
        }

        TLogAspectExt tlogAspect = (TLogAspectExt) method.getAnnotation(TLogAspectExt.class);
//        String[] aspectExpressions = tlogAspect.value();
//        String str = tlogAspect.str();
//        String pattern = tlogAspect.pattern();
//        String joint = tlogAspect.joint();
//        Class<? extends AspectLogConvert> convertClazz = tlogAspect.convert();
        Object proceed = null;
        Exception exception = null;
        try {
            return jp.proceed();
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            this.convert(tlogAspect, method.getName(), paramNameValueMap, proceed, startTime, exception);
        }


    }

    public void convert(TLogAspectExt tlogAspect, String method, Map<String, Object> paramNameValueMap, Object result, LocalDateTime startTime, Throwable exception) {
        if (!tlogAspect.enable()) {
            return;
        }
        if (tlogAspect.type() != null && (Arrays.asList(tlogAspect.type()).contains(OperateTypeEnum.GET))) {
            return;
        }
        OperateLogCreateReqDTO operateLogCreateReqDTO = new OperateLogCreateReqDTO();


        operateLogCreateReqDTO.setJavaMethod(method);
        operateLogCreateReqDTO.setJavaMethodArgs(JSONUtil.toJsonStr(paramNameValueMap));
        operateLogCreateReqDTO.setResultData(JSONUtil.toJsonStr(result));
        operateLogCreateReqDTO.setDuration((int) (LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis()));
        // （正常）处理 resultCode 和 resultMsg 字段
        if (result instanceof CommonResult) {
            CommonResult<?> commonResult = (CommonResult<?>) result;
            operateLogCreateReqDTO.setResultCode(commonResult.getCode());
            operateLogCreateReqDTO.setResultMsg(commonResult.getMsg());
        } else {
            operateLogCreateReqDTO.setResultCode(LoginResultEnum.SUCCESS.getResult());
        }
        // （异常）处理 resultCode 和 resultMsg 字段
        if (exception != null) {
            operateLogCreateReqDTO.setResultCode(INTERNAL_SERVER_ERROR.getCode());
            operateLogCreateReqDTO.setResultMsg(ExceptionUtil.getRootCauseMessage(exception));
        }

        // 补全模块信息
        operateLogCreateReqDTO.setModule(tlogAspect.moduleName());
        operateLogCreateReqDTO.setName(tlogAspect.str());
        if (tlogAspect.type() != null && tlogAspect.type().length > 0) {
            operateLogCreateReqDTO.setType(tlogAspect.type()[0].getType());
        } else {
            operateLogCreateReqDTO.setType(0);
        }

        operateLogCreateReqDTO.setTraceId("<" + TLogContext.getTraceId() + "><" + TLogContext.getSpanId() + ">");
        operateLogCreateReqDTO.setStartTime(startTime);

        // 补全请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            operateLogCreateReqDTO.setUserIp(ServletUtil.getClientIP(request, "Ip"));
            operateLogCreateReqDTO.setRequestUrl(SaHolder.getRequest().getRequestPath());
            operateLogCreateReqDTO.setUserAgent(ServletUtils.getUserAgent());
            operateLogCreateReqDTO.setRequestMethod(request.getMethod());
        }


        if (StpPlayerUtil.isLogin()) {
            // 补全用户信息
            operateLogCreateReqDTO.setUserId(StpPlayerUtil.getLoginIdAsLong());
            operateLogCreateReqDTO.setUserType(UserTypeEnum.MEMBER.getValue());
        }
        try {
            log.info("开始记录操作日志，类型：{},方法：{},请求参数：{}，响应:{}，开始时间：{}，需要记录的信息为：{}", JSONUtil.toJsonStr(tlogAspect), method, JSONUtil.toJsonStr(paramNameValueMap), JSONUtil.toJsonStr(result), startTime, JSONUtil.toJsonStr(operateLogCreateReqDTO));
        } catch (Exception exception1) {
            log.error("开始记录操作日志，类型：{},方法：{},请求参数：{}，开始时间：{}，需要记录的信息为：{},异常：{}", JSONUtil.toJsonStr(tlogAspect), method, JSONUtil.toJsonStr(paramNameValueMap), JSONUtil.toJsonStr(result), startTime, JSONUtil.toJsonStr(operateLogCreateReqDTO), exception);
        }
        if (operateLogCreateReqDTO.getUserId() == null) {
            return;
        }
        SpringUtil.getBean("operateLogServiceImpl", OperateLogService.class).saveOperateLog(operateLogCreateReqDTO);
    }

}
