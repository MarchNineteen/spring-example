package com.wyb.test.spring.ioc;

import com.wyb.test.spring.smallSpring.ioc.xml.XmlBeanFactory;
import org.junit.Test;

/**
 * * http://www.tianxiaobo.com/2018/01/18/%E8%87%AA%E5%B7%B1%E5%8A%A8%E6%89%8B%E5%AE%9E%E7%8E%B0%E7%9A%84-Spring-IOC-%E5%92%8C-AOP-%E4%B8%8B%E7%AF%87/#2-ioc-%E7%9A%84%E5%AE%9E%E7%8E%B0
 */
public class XmlBeanFactoryTest {

    @Test
    public void getBean() throws Exception {
        System.out.println("--------- IOC test ----------");
        String location = getClass().getClassLoader().getResource("small-spring.xml").getFile();
        XmlBeanFactory bf = new XmlBeanFactory(location);
//        com.wyb.test.spring.Wheel wheel = (com.wyb.test.spring.Wheel) bf.getBean("wheel");
//        System.out.println(wheel);
//        com.wyb.test.spring.Car car = (com.wyb.test.spring.Car) bf.getBean("car");
//        System.out.println(car);
//
        System.out.println("\n--------- AOP test ----------");
//        com.wyb.test.spring.HelloService helloService = (com.wyb.test.spring.HelloService) bf.getBean("helloService");
//        helloService.sayHelloWorld();
    }
}
