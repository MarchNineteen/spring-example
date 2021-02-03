package com.wyb.test.spring.simpleSpring;

import org.junit.Test;

import com.wyb.test.spring.HelloService;
import com.wyb.test.spring.HelloServiceImpl;
import com.wyb.test.spring.simpleSpring.aop.Advice;
import com.wyb.test.spring.simpleSpring.aop.BeforeAdvice;
import com.wyb.test.spring.simpleSpring.aop.MethodInvocation;
import com.wyb.test.spring.simpleSpring.aop.SimpleAop;

public class SimpleAopTest {

    @Test
    public void getProxy() throws Exception {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAop.getProxyObject(helloServiceImpl, beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
