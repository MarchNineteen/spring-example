package com.wyb.test.spring.simpleSpring.aop;

import java.lang.reflect.Proxy;

/**
 * @author Marcher丶 代理生成类
 */
public class SimpleAop {

    /**
     * 生成代理对象
     *
     * @param object
     * @param advice
     * @return
     */
    public static Object getProxyObject(Object object, Advice advice) {
        return Proxy.newProxyInstance(advice.getClass().getClassLoader(), object.getClass().getInterfaces(), advice);
    }
}
