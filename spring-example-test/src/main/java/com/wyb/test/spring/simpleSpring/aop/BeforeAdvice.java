package com.wyb.test.spring.simpleSpring.aop;

import java.lang.reflect.Method;

/**
 * @author Marcher丶
 */
public class BeforeAdvice implements Advice {

    // 实际对象impl
    private Object bean;
    private MethodInvocation methodInvocation;


    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }

}
