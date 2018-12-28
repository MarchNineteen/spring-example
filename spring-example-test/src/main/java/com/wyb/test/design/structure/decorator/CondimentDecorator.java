package com.wyb.test.design.structure.decorator;

/**
 * @author Kunzite
 */
public abstract class CondimentDecorator implements Beverage {

    protected Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
