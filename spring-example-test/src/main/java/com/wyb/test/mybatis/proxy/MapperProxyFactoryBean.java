package com.wyb.test.mybatis.proxy;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import com.wyb.test.mybatis.config.Metadata;

/**
 * @author Marcher丶
 */
public class MapperProxyFactoryBean<T> implements FactoryBean<T> {

    private Metadata metadata;

    /**
     * 代理工厂创建实际bean
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getObject() throws Exception {
        // return (T) MapperJdkProxyCreator.getProxyMapper(metadata.getClazz());
        return (T) Proxy.newProxyInstance(metadata.getClazz().getClassLoader(), new Class[] { metadata.getClazz() },
                new MyMybatisInvocationHandler());

    }

    @Override
    public Class<?> getObjectType() {
        return metadata.getClazz();
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
