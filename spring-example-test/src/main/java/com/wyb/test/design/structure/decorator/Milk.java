package com.wyb.test.design.structure.decorator;

/**
 * @author Kunzite
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }
}
