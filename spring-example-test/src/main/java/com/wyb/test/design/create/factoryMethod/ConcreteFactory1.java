package com.wyb.test.design.create.factoryMethod;

/**
 * @author Kunzite
 */
public class ConcreteFactory1 extends Factory {

    @Override
    public Product factoryMethod() {
        return new ConcreteProduct1();
    }
}
