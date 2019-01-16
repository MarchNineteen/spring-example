package com.wyb.test.design.behavioral.strategy;

/**
 * @author Kunzite
 */
public class Duck {

    private CallBehavior behavior;

    public void performQuack() {
        if (behavior != null) {
            behavior.call();
        }
    }
    public void setQuackBehavior(CallBehavior behavior) {
        this.behavior = behavior;
    }
}
