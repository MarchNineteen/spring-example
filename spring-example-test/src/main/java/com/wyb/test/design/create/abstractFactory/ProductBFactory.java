package com.wyb.test.design.create.abstractFactory;

/**
 * @author Kunzite
 */
public class ProductBFactory extends Factory {

    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
