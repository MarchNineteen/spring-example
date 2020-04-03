package com.wyb.test.spring.smallSpring.aop.advisor;

import java.lang.reflect.Method;

public interface MethodMatcher {

    Boolean matchers(Method method, Class beanClass);
}
