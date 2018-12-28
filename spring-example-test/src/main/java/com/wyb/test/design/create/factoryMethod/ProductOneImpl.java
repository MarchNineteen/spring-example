package com.wyb.test.design.create.factoryMethod;

/**
 * @author Kunzite
 */
public class ProductOneImpl extends Factory implements Product {
    @Override
    public Product factoryMethod() {
        return new ProductOneImpl();
    }
}
