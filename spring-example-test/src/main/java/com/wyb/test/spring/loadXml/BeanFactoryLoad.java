package com.wyb.test.spring.loadXml;

import com.wyb.test.java.common.BaseEntity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Marcher丶
 */
public class BeanFactoryLoad {

    public static void main(String[] args) {
        BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(reg);
        reader.loadBeanDefinitions(new ClassPathResource("appcontext.xml"));
        reader.loadBeanDefinitions(new ClassPathResource("appcontext2.xml"));
        BeanFactory bf = (BeanFactory) reg;
        BaseEntity entity = (BaseEntity) bf.getBean("baseEntity");
        BaseEntity entity2 = (BaseEntity) bf.getBean("baseEntity2");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity.getId());
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity2.getId());
    }
}
