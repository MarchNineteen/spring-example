package com.wyb.test.design.factoryMethod;

/**
 * @author Kunzite
 */
public class ProductTwoImpl extends Factory implements Product {
    @Override
    public Product factoryMethod() {
        return new ProductTwoImpl();
    }
}
