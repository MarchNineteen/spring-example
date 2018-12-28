package com.wyb.test.design.create.abstractFactory;

/**
 * @author Kunzite
 */
public class ProductAFactory extends Factory {

    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
