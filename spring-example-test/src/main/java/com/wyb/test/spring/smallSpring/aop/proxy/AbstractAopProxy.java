package com.wyb.test.spring.smallSpring.aop.proxy;

import com.wyb.test.spring.smallSpring.aop.advisor.AdvisedSupport;

import java.lang.reflect.InvocationHandler;

/**
 * @author Marcher丶
 */
public abstract class AbstractAopProxy implements AopProxy, InvocationHandler {

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
