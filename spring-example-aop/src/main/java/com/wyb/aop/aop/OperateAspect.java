package com.wyb.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-03-05 10:31
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class OperateAspect {

    //配置切入点
    @Pointcut("execution(* com.wyb.aop.controller..*.*(..))")
    public  void catchAll() {
    }

    /**
     * 切面的前置方法 即方法执行前拦截到的方法
     * 在目标方法执行之前的通知
     * @param jp
     */
    @Before("catchAll()")
    public void doBefore(JoinPoint jp){
        log.info("=========执行前置通知==========");
        System.out.println("=========执行前置通知==========");
    }


    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning
     * @param jp
     * @param result
     */
    @AfterReturning(value="catchAll()",returning="result")
    public void doAfterReturning(JoinPoint jp,String result){
        System.out.println("===========执行后置通知============");
    }

    /**
     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
     * @param jp
     */
    @After(value="catchAll()")
    public void doAfter(JoinPoint jp){
        System.out.println("===========执行最终通知============");
    }

    /**
     * 环绕通知：目标方法调用前后执s行的通知，可以在方法调用前后完成自定义的行为。
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("catchAll()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("======执行环绕通知开始=========");
        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象
        Object target = pjp.getTarget();
        // 执行完方法的返回值
        // 调用proceed()方法，就会触发切入点方法执行
        Object result=pjp.proceed();
        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
        System.out.println("======执行环绕通知结束=========");
        return result;
    }

    /**
     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
     * @param jp
     * @param ex
     */
    @AfterThrowing(value="catchAll()",throwing="ex")
    public void doAfterThrowing(JoinPoint jp, Exception ex) {
        System.out.println("===========执行异常通知============");
    }
}
