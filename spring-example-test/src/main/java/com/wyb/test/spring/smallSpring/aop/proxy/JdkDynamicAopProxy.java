package com.wyb.test.spring.smallSpring.aop.proxy;

import com.wyb.test.spring.smallSpring.aop.advisor.AdvisedSupport;
import com.wyb.test.spring.smallSpring.aop.advisor.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Marcher丶
 */
public class JdkDynamicAopProxy extends AbstractAopProxy {

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        super(advised);
    }

    /**
     * 为目标 bean 生成代理对象
     *
     * @return bean 的代理对象
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getInterfaces(), this);
    }

    /**
     * InvocationHandler 接口中的 invoke 方法具体实现，封装了具体的代理逻辑
     *
     * @param proxy
     * @param method
     * @param args
     * @return 代理方法或原方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMatcher methodMatcher = advised.getMethodMatcher();

        // 1. 使用方法匹配器 methodMatcher 测试 bean 中原始方法 method 是否符合匹配规则
        if (null != methodMatcher && methodMatcher.matchers(method, advised.getTargetSource().getTargetClass())) {
            // 获取 Advice MethodInterceptor 的父接口继承了 advice
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            /*
             * 2. 将 bean 的原始方法 method 封装在 MethodInvocation 接口实现类对象中，
             * 并把生成的对象作为参数传给 advice 实现类对象，执行通知逻辑
             */
            return methodInterceptor.invoke(
                    new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));

        } else {
            // 2. 当前 method 不符合匹配规则，直接调用 bean 的原始方法 method
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
