package com.wyb.test.spring.bean;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author Kunzite
 */
public class LifeCycle implements BeanNameAware {

    public void instance() {

    }

    @Override
    public void setBeanName(String s) {

    }
}
