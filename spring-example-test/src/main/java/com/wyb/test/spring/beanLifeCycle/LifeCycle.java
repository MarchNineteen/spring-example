package com.wyb.test.spring.beanLifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kunzite
 */
public class LifeCycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String attr;

    public LifeCycle() {
        System.out.println("实例化");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("beanNameAware setBeanName方法");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware setBeanFactory方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContext setApplicationContext方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void customInit() {
        System.out.println("定制化初始方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy方法");
    }

    public void customDestroy() {
        System.out.println("定制化销毁方法");
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        System.out.println("填充属性");
        this.attr = attr;
    }

    public static void main(String[] args) {
        // 单个文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springBeanLifeCycle.xml");
        LifeCycle lifeCycle = (LifeCycle) applicationContext.getBean("lifeCycle");
        System.out.println("lifeCycle：" + lifeCycle.getAttr());
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
