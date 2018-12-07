package com.wyb.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kunzite
 */
@Aspect
@Component
public class TimeLogAspect {

    private static Logger log = LoggerFactory.getLogger(TimeLogAspect.class);

    // 创建以线程ID为key value为对应方法开始时间
    private Map<Long, Map<String, List<Long>>> threadMap = new ConcurrentHashMap<>(200);

    @Pointcut("@annotation(com.wyb.aop.annotation.TimeLog)")
    public void catchAll() {
    }

    @Before("catchAll()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        log.debug("类:{},方法:{}开始:", signature.getDeclaringTypeName(), signature.getName());

        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        List<Long> list;
        if (null == methodTimeMap) {
            methodTimeMap = new HashMap<>();
            list = new LinkedList<>();
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
            threadMap.put(Thread.currentThread().getId(), methodTimeMap);
        }
        else {
            list = methodTimeMap.get(joinPoint.toShortString());
            if (null == list) {
                list = new LinkedList<>();
            }
            list.add(System.currentTimeMillis());
            methodTimeMap.put(joinPoint.toShortString(), list);
        }
    }

    @After("catchAll()")
    public void doAfterAdvice(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
//        log.debug("类:{},方法:{}结束", signature.getDeclaringTypeName(), signature.getName());
        Map<String, List<Long>> methodTimeMap = threadMap.get(Thread.currentThread().getId());
        if (null != methodTimeMap) {
            List<Long> list = methodTimeMap.get(joinPoint.toShortString());
            if (null != list) {
                log.debug("类{},方法{} 耗时:{}毫秒", signature.getDeclaringTypeName(), signature.getName(), (System.currentTimeMillis() - list.get(list.size() -1)));
                list.remove(list.size() - 1);
            }
            return;
        }
        log.debug("类{},方法{} 计算失败，未找到相应线程对于开始时间", signature.getDeclaringTypeName(), signature.getName());
    }
}
