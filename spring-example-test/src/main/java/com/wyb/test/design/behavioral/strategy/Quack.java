package com.wyb.test.design.behavioral.strategy;

/**
 * @author Kunzite
 * 鸭叫
 */
public class Quack implements CallBehavior {

    @Override
    public void call() {
        System.out.println("鸭子叫!");
    }
}
