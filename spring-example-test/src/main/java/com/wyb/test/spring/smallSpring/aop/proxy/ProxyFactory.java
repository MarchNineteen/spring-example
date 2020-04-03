package com.wyb.test.spring.smallSpring.aop.proxy;

import com.wyb.test.spring.smallSpring.aop.advisor.AdvisedSupport;

public class ProxyFactory extends AdvisedSupport implements AopProxy {

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        return new JdkDynamicAopProxy(this);
    }
}
