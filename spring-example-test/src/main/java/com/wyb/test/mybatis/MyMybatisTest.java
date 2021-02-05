package com.wyb.test.mybatis;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wyb.test.mybatis.config.EnableMyImport;
import com.wyb.test.mybatis.dao.UserMapper;

/**
 * @author Marcher丶
 */
public class MyMybatisTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                EnableMyImport.class);
        UserMapper mapper = (UserMapper) applicationContext.getBean("userMapper");
        System.out.println(mapper.getUserById(1L));
    }
}
