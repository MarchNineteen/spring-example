package com.wyb.test.mybatis;

import com.wyb.test.mybatis.config.MyMybatisBeanDefinitionRegistryPostProcessor;
import com.wyb.test.mybatis.dao.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Marcher丶
 */
public class MyMybatisTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyMybatisBeanDefinitionRegistryPostProcessor.class);
        UserMapper mapper = (UserMapper) applicationContext.getBean("userMapper");
        System.out.println(mapper.getUserById(1L));
    }
}
