package com.wyb.test.spring.cycleDependence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Marcher丶
 */
public class CycleMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CycleConfig.class);
        CdA cdA = (CdA) context.getBean("CdA");
        System.out.println(cdA);
    }

//    public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry {
//        private final Map<String, Object> singletonObjects = new ConcurrentHashMap(256);
//        private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap(16);
//        private final Map<String, Object> earlySingletonObjects = new HashMap(16);
//    }

//    AbstractAutowireCapableBeanFactory.createBean()


}
