package com.wyb.test.spring.smallSpring.aop.advisor;


public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
