package com.wyb.test.spring.smallSpring.ioc.factory;

/**
 * @author Marcher丶
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
