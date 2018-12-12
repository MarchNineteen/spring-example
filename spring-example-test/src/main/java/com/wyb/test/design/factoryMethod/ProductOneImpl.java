package com.wyb.test.design.factoryMethod;

/**
 * @author Kunzite
 */
public class ProductOneImpl extends Factory implements Product {
    @Override
    public Product factoryMethod() {
        return new ProductOneImpl();
    }
}
