package com.wyb.test.mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wyb.test.mybatis.config.EnableMyImport;
import com.wyb.test.mybatis.dao.UserMapper;
import com.wyb.test.mybatis.dao.UserMapper1;

/**
 * @author Marcher丶
 */
public class MyMybatisTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                EnableMyImport.class);
        UserMapper mapper = (UserMapper) applicationContext.getBean("userMapper");
        System.out.println(mapper.getUserById(1L));

        UserMapper1 userMapper1 = (UserMapper1) applicationContext.getBean("userMapper1");
        System.out.println(userMapper1.getUserById(1L));
    }
}
