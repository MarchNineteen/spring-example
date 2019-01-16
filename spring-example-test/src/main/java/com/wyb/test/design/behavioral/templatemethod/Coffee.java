package com.wyb.test.design.behavioral.templatemethod;

/**
 * @author Kunzite
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Coffee.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }
}
