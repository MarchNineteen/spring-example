package com.wyb.test.mybatis.config;

import com.wyb.test.mybatis.ScannerPackageClassTool;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.wyb.test.mybatis.proxy.MapperProxyFactoryBean;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Marcher丶
 */
public class MyMybatisImportBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {

    private List<Metadata> metadatas;
    private final Map<Class<?>, MapperProxyFactoryBean<?>> knownMappers = new HashMap();

    public MyMybatisImportBeanDefinitionRegistry() {
        this.metadatas = new ArrayList<>();
        Set<Class<?>>  classes = ScannerPackageClassTool.getClasses("com.wyb.test.mybatis.dao");
        for (Class<?> clazz : classes) {
            Metadata metadata = new Metadata(clazz);
            metadatas.add(metadata);
            knownMappers.put(clazz, new MapperProxyFactoryBean<>());
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
            BeanDefinitionRegistry beanDefinitionRegistry) {

        if (!CollectionUtils.isEmpty(metadatas)) {
            metadatas.forEach(metadata -> {
                BeanDefinitionBuilder db = BeanDefinitionBuilder.genericBeanDefinition(knownMappers.get(metadata.getClazz()).getClass());
                db.addPropertyValue("metadata", metadata);
                BeanDefinition beanDefinition = db.getBeanDefinition();
                beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
                beanDefinitionRegistry.registerBeanDefinition(metadata.getName(), beanDefinition);
            });
        }
    }

}
