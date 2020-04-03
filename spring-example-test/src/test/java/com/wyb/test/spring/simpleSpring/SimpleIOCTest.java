package com.wyb.test.spring.simpleSpring;

import com.wyb.test.spring.Car;
import com.wyb.test.spring.Wheel;
import com.wyb.test.spring.simpleSpring.ioc.SimpleSpringIoc;
import org.junit.Test;

public class SimpleIOCTest {

    @Test
    public void getBean() throws Exception {
        String location = SimpleSpringIoc.class.getClassLoader().getResource("simple-spring-ioc.xml").getFile();
        SimpleSpringIoc bf = new SimpleSpringIoc(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
