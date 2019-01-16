package com.wyb.test.design.behavioral.strategy;

/**
 * @author Kunzite
 */
public class Squeak implements CallBehavior {

    @Override
    public void call() {
        System.out.println("鸡叫k!");
    }
}
