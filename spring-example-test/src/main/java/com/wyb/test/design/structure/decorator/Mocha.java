package com.wyb.test.design.structure.decorator;

/**
 * @author Kunzite
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }
}
