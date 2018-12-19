package com.shimh.common.aspect;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.util.HttpContextUtil;
import com.shimh.common.util.IpUtil;
import com.shimh.common.util.HttpContextUtil;
import com.shimh.common.util.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author: shimh
 * @create: 2018年08月
 **/
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Around("@annotation(com.shimh.common.annotation.LogAnnotation)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

        String module = logAnnotation.module();
        String operation = logAnnotation.operation();

        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IpUtil.getIpAddr(request);

        String methodName = this.getMethodName(joinPoint);
        String requester = "unknown";

        String userAgent = request.getHeader("user-agent");

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        logger.info("access info log: requester [{}] module [{}] operation [{}] method [{}] IP [{}] userAgent [{}] cost [{}] millis ",
                requester, module, operation, methodName, ip, userAgent, System.currentTimeMillis() - start);

        return result;
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        return className + "." + methodName;
    }

}
