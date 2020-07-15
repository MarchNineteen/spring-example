package com.wyb.test.design.behavioral.strategy.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Marcher丶
 */
public class StrategyContext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(QueryTicketStrategyContext.class).getTicketList(1);
    }
}
