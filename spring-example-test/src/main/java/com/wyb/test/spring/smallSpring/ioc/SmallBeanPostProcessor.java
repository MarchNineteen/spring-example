package com.wyb.test.spring.smallSpring.ioc;

/**
 * @author Marcher丶
 */
public class SmallBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) throws Exception {
        System.out.println("postProcessBeforeInitialization " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) throws Exception {
        System.out.println("postProcessAfterInitialization " + beanName);
        return bean;
    }
}
