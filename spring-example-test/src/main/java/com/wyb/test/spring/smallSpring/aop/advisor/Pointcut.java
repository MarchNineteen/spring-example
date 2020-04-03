package com.wyb.test.spring.smallSpring.aop.advisor;

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
