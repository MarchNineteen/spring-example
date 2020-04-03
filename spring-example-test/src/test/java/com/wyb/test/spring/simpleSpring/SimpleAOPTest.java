package com.wyb.test.spring.simpleSpring;

import com.wyb.test.spring.HelloService;
import com.wyb.test.spring.HelloServiceImpl;
import com.wyb.test.spring.simpleSpring.aop.Advice;
import com.wyb.test.spring.simpleSpring.aop.BeforeAdvice;
import com.wyb.test.spring.simpleSpring.aop.MethodInvocation;
import com.wyb.test.spring.simpleSpring.aop.SimpleAOP;
import org.junit.Test;

public class SimpleAOPTest {

    @Test
    public void getProxy() throws Exception {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxyObject(helloServiceImpl,beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
