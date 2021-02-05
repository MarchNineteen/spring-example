package com.wyb.test.mybatis.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Marcher丶 创建mapper的代理对象
 */
public class MapperJdkProxyCreator<T> {

    @SuppressWarnings("unchecked")
    public T getProxyMapper(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[] { cls }, new MyMybatisInvocationHandler());
    }

    public static void main(String[] args) {
        // UserMapper o = (UserMapper) MapperJdkProxyCreator.getProxyMapper(UserMapper.class);
        // System.out.println(o.getUserById(1L));
    }
}
