package com.wyb.test.spring.smallSpring.ioc;

import com.wyb.test.spring.smallSpring.ioc.bean.BeanDefinition;

import java.util.Map;

/**
 * @author Marcher丶
 */
public interface BeanDefinitionReader {

    Map<String, BeanDefinition> getRegistry();

    void loadBeanDefinitions(String location) throws Exception;
}
