package com.wyb.test.mybatis.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.wyb.test.mybatis.dao.UserMapper;
import com.wyb.test.mybatis.proxy.MapperProxyFactoryBean;

/**
 * @author Marcher丶
 */
public class MyMybatisImportBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {

    private Metadata metadata;

    public MyMybatisImportBeanDefinitionRegistry() {
        this.metadata = new Metadata(UserMapper.class);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
            BeanDefinitionRegistry beanDefinitionRegistry) {

        BeanDefinitionBuilder db = BeanDefinitionBuilder.genericBeanDefinition(MapperProxyFactoryBean.class);
        db.addPropertyValue("metadata", metadata);
        BeanDefinition beanDefinition = db.getBeanDefinition();
        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        beanDefinitionRegistry.registerBeanDefinition(metadata.getName(), beanDefinition);
    }

}
