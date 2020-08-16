package com.wyb.test.mybatis.proxy;

import com.wyb.test.mybatis.config.Metadata;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Marcher丶
 */
public class MapperProxyFactoryBean implements FactoryBean<Object> {

    private Metadata metadata;

    /**
     * 代理工厂创建实际bean
     */
    @Override
    public Object getObject() throws Exception {
        return MapperJdkProxyCreator.getProxyMapper(metadata.getClazz());
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
        return false;
    }

}
