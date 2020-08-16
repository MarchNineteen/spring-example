package com.wyb.test.mybatis.proxy;

import com.wyb.test.mybatis.dao.UserMapper;

import java.lang.reflect.Proxy;

/**
 * @author Marcher丶
 * 创建mapper的代理对象
 */
public class MapperJdkProxyCreator {

    public static Object getProxyMapper(Class<?> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{cls}, new MyMybatisInvocationHandler());
    }

    public static void main(String[] args) {
        UserMapper o = (UserMapper) MapperJdkProxyCreator.getProxyMapper(UserMapper.class);
        System.out.println(o.getUserById(1L));
    }
}
