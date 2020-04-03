package com.wyb.test.spring.smallSpring.aop.advisor;

public interface ClassFilter {

    Boolean matchers(Class beanClass) throws Exception;
}
