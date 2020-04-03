package com.wyb.test.spring.aop;

import com.wyb.test.spring.HelloService;
import com.wyb.test.spring.HelloServiceImpl;
import com.wyb.test.spring.smallSpring.aop.advisor.AdvisedSupport;
import com.wyb.test.spring.smallSpring.aop.advisor.TargetSource;
import com.wyb.test.spring.smallSpring.aop.proxy.JdkDynamicAopProxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class JdkDynamicAopProxyTest {

    @Test
    public void getProxy() throws Exception {
        System.out.println("---------- no proxy ----------");
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHelloWorld();

        System.out.println("\n----------- proxy -----------");
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new LogInterceptor());

        TargetSource targetSource = new TargetSource(
                helloService, HelloServiceImpl.class, HelloServiceImpl.class.getInterfaces());
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher((Method method, Class beanClass) -> true);

        helloService = (HelloService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        helloService.sayHelloWorld();
    }
}
