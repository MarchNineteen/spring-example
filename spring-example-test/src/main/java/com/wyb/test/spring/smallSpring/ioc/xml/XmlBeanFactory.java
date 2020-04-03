package com.wyb.test.spring.smallSpring.ioc.xml;

import com.wyb.test.spring.smallSpring.ioc.BeanDefinitionReader;
import com.wyb.test.spring.smallSpring.ioc.BeanPostProcessor;
import com.wyb.test.spring.smallSpring.ioc.bean.BeanDefinition;
import com.wyb.test.spring.smallSpring.ioc.bean.BeanReference;
import com.wyb.test.spring.smallSpring.ioc.bean.PropertyValue;
import com.wyb.test.spring.smallSpring.ioc.bean.PropertyValues;
import com.wyb.test.spring.smallSpring.ioc.factory.BeanFactory;
import com.wyb.test.spring.smallSpring.ioc.factory.BeanFactoryAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcher丶
 */
public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private List<String> beanDefinitionNames = new ArrayList<>();

    private BeanDefinitionReader beanDefinitionReader;

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    public XmlBeanFactory(String location) throws Exception {
        this.beanDefinitionReader = new XmlBeanDefinitionReader();
        loadBeanDefinitions(location);
        registerBeanPostProcessor();
    }

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (null == beanDefinition) {
            throw new IllegalArgumentException("no this bean with name " + name);
        }
        Object instance = beanDefinition.getBean();
        if (null == instance) {
            // 实例化
            instance = createBean(beanDefinition);
            // 填充属性
            applyProperties(instance, beanDefinition);
            // 初始化 beanPost
            initializeBean(instance, name);

            beanDefinition.setBean(instance);
        }
        return instance;
    }

    // 初始化
    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(name, bean);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(name, bean);
        }

        return bean;
    }

    // 实例化
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object instance = beanDefinition.getBeanClass().newInstance();
        return instance;
    }

    // 填充属性
    private void applyProperties(Object instance, BeanDefinition beanDefinition) throws Exception {
        if (instance instanceof BeanFactoryAware) {
            ((BeanFactoryAware) instance).setBeanFactory(this);
        }
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues.getValues().size() > 0) {
            for (PropertyValue pv : propertyValues.getValues()) {
                Object value = pv.getValue();
                String name = pv.getName();
                if (value instanceof BeanReference) {
                    BeanReference br = (BeanReference) value;
                    value = getBean(br.getName());
                }
                try {
                    Method declaredMethod = instance.getClass().getDeclaredMethod("set" + upperFirstCase(name), value.getClass());
                    declaredMethod.setAccessible(true);

                    declaredMethod.invoke(instance, value);
                } catch (Exception ex) {
                    Field declaredField = instance.getClass().getDeclaredField(name);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, value);
                }

            }
        }
    }

    private static String upperFirstCase(String word) {
        char[] chars = word.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }


    private void loadBeanDefinitions(String location) throws Exception {
        beanDefinitionReader.loadBeanDefinitions(location);
        registerBeanDefinition();
    }

    private void registerBeanDefinition() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionReader.getRegistry().entrySet()) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            beanDefinitionMap.put(name, beanDefinition);
            beanDefinitionNames.add(name);
        }
    }

    public void registerBeanPostProcessor() throws Exception {
        // 是否是beanPost
        List beans = getBeansForType(BeanPostProcessor.class);
        for (Object bean : beans) {
            addBeanPostProcessor((BeanPostProcessor) bean);
        }
    }

    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }


}
