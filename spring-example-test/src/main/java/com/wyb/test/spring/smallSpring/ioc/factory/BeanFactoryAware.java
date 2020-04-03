package com.wyb.test.spring.smallSpring.ioc.factory;

/**
 * @author Marcher丶
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
