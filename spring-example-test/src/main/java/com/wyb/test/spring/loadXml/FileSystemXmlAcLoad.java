package com.wyb.test.spring.loadXml;

import com.wyb.test.java.common.BaseEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Marcher丶
 */
public class FileSystemXmlAcLoad {

    public static void main(String[] args) {
        // classPath
        ApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:appcontext.xml");
        // 文件系统
        ApplicationContext fileSystemXmlApplicationContext2 = new FileSystemXmlApplicationContext("spring-example-test/src/main/resources/appcontext.xml");
        ApplicationContext fileSystemXmlApplicationContext3 = new FileSystemXmlApplicationContext("file:E:\\marcher\\spring-example\\spring-example-test\\src\\main\\resources/appcontext.xml");
        ApplicationContext fileSystemXmlApplicationContext4 = new FileSystemXmlApplicationContext("E:\\marcher\\spring-example\\spring-example-test\\src\\main\\resources/appcontext.xml");

        BaseEntity entity = (BaseEntity) fileSystemXmlApplicationContext.getBean("baseEntity");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity.getId());

        BaseEntity entity2 = (BaseEntity) fileSystemXmlApplicationContext2.getBean("baseEntity");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity2.getId());

        BaseEntity entity3 = (BaseEntity) fileSystemXmlApplicationContext3.getBean("baseEntity");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity2.getId());

        BaseEntity entity4 = (BaseEntity) fileSystemXmlApplicationContext4.getBean("baseEntity");
        System.out.println("ClassPathXmlApplicationContext 获取 bean ：" + entity2.getId());

    }
}
