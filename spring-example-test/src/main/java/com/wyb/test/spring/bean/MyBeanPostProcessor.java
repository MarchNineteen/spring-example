package com.wyb.test.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor类是为了让创建其他类的时候进行创建前后的一些操作，这么写一般是不会调用postProcessBeforeInitialization()和postProcessAfterInitialization()方法的。
 * 原因就是。在容器初始化定义的bean创建之前，容器会自己去查找所有的beanPostProcessor进行创建，你自定义的类，由于是实现了BeanPostProcessor接口，
 * 因此这时候会进行BeanPostProcessor的创建和注册，源码中，在注册BeanPostProcessor会进行getBean操作，即创建自定义的bean。
 * 由于默认的是单例模式，因此后面再次进行获取就不会再次调用postProcessBeforeInitialization()和postProcessAfterInitialization()方法，
 * 因为已经放入了spring缓存，直接获取，不需要实例，因此没有调用。如果你真的想使用的时候调用postProcessBeforeInitialization()和postProcessAfterInitialization()方法，
 * 将你的bean设置为原型模式（prototype），这样每次调用都会创建，因此初始化容器之后每次都会调用的。
 *
 * @author Marcher丶
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessBeforeInitialization方法");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessAfterInitialization方法");
        return bean;
    }
}
