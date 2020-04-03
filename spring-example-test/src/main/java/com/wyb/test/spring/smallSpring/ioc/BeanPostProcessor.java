package com.wyb.test.spring.smallSpring.ioc;

/**
 * @author Marcher丶
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName, Object bean) throws Exception;

    Object postProcessAfterInitialization(String beanName, Object bean) throws Exception;
}
