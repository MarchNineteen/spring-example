package com.wyb.test.spring.loadXml;

import com.wyb.test.java.common.BaseEntity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Marcher丶
 */
public class XmlBeanFactoryLoad {

    public static void main(String[] args) {
        Resource resource = new ClassPathResource("appcontext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        BaseEntity entity = (BaseEntity) factory.getBean("baseEntity1");
        System.out.println("XmlBeanFactory 获取 bean ：" + entity.getId());
    }
}
