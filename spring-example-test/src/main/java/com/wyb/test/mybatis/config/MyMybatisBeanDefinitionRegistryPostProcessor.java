package com.wyb.test.mybatis.config;

import com.wyb.test.mybatis.dao.UserMapper;
import com.wyb.test.mybatis.proxy.MapperProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author Marcher丶
 */
public class MyMybatisBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private Metadata metadata;

    public MyMybatisBeanDefinitionRegistryPostProcessor() {
        this.metadata = new Metadata(UserMapper.class);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder db = BeanDefinitionBuilder.genericBeanDefinition(MapperProxyFactoryBean.class);
        db.addPropertyValue("metadata", metadata);
        BeanDefinition beanDefinition = db.getBeanDefinition();
        beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        registry.registerBeanDefinition(metadata.getName(), beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
