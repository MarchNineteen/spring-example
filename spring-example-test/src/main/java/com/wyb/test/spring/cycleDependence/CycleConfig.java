package com.wyb.test.spring.cycleDependence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Marcher丶
 * <p>
 * 构造器注入存在循环依赖的问题 使用@lazy进行懒加载 循环依赖的类都需要加
 * setter方式比较循环依赖
 */
@Configuration
@ImportResource("classpath:springCycleDI.xml")
public class CycleConfig {

//    @Bean
//    @Lazy
//    public CdA CdA(CdB cdB) {
//        return new CdA(cdB);
//    }
//
//    @Bean
//    @Lazy
//    public CdB CdB(CdC cdC) {
//        return new CdB(cdC);
//    }

//    @Bean
//    public CdA CdA() {
//        CdA cdA = new CdA();
//        cdA.setCdB(CdB());
//        return cdA;
//    }
//
//    @Bean
//    public CdB CdB() {
//        CdB cdB = new CdB();
//        cdB.setCdA(CdA());
//        return cdB;
//    }

}
