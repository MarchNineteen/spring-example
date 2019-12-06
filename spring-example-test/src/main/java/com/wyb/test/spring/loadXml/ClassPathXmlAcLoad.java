package com.wyb.test.spring.loadXml;

import com.wyb.test.java.common.BaseEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Marcher丶
 */
public class ClassPathXmlAcLoad {

    public static void main(String[] args) {
        // 单个文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
        // 多个文件
        ApplicationContext multiApplicationContext = new ClassPathXmlApplicationContext("classpath:appcontext.xml", "classpath:appcontext2.xml");
        BaseEntity entity = (BaseEntity) applicationContext.getBean("baseEntity");

        BaseEntity entity2 = (BaseEntity) multiApplicationContext.getBean("baseEntity2");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity.getId());
        System.out.println("ClassPathXmlApplicationContext 获取 bean2 ：" + entity2.getId());
    }
}
