package com.wyb.test.design.create.factoryMethod;

/**
 * @author Kunzite
 */
public class ConcreteFactory extends Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
