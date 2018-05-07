package com.wyb.exception.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
@Order(1)
@Slf4j
public class ExceptionLogAspect {

    @Pointcut("execution(* com.wyb.exception.controller..*.* (..))")
    private void catchControllerException() {
    }

    /**
     * 异常捕获
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("catchControllerException()")
    public Object doControllerThrowing(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("异常错误信息如下：\n", e);
            return e.getMessage();
        }
    }

    /**
     * controller 入参日志打印
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before(value = "catchControllerException() ")
    public void doControllerParams(JoinPoint joinPoint) throws Throwable {
        List<Object> args = new ArrayList<>();
        String functionName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        // ApiErrorController 入参过滤
        if (joinPoint.getTarget().getClass().getName().endsWith("ApiErrorController")) {
            return;
        }
        for (Object object : joinPoint.getArgs()) {
            if (object != null && (!object.getClass().getName().contains("HttpServletRequest")
                && !object.getClass().getName().contains("RequestFacade")
                && !object.getClass().getName().contains("HttpServletResponse")
                && !object.getClass().getName().contains("ResponseFacde")
                && !object.getClass().getName().contains("org.springframework.security"))) {
                args.add(object);
            }
        }
        if (!CollectionUtils.isEmpty(args)) {
            log.info("{} before args:{}", functionName, JSON.toJSON(args).toString());
        }
    }

    /**
     * controller 出参日志打印
     *
     * @param joinPoint
     * @param rtv
     * @throws Throwable
     */
    @AfterReturning(value = "catchControllerException()", returning = "rtv")
    public void doControllerReturnParams(JoinPoint joinPoint, Object rtv) throws Throwable {
        String functionName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
        log.info("{} after result:{}", functionName, JSON.toJSON(rtv).toString());
    }
}
