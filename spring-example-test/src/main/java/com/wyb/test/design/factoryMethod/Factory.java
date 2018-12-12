package com.wyb.test.design.factoryMethod;

/**
 * @author Kunzite
 */
public abstract class Factory {

    abstract public Product factoryMethod();

    public void doSomething() {
        Product product = factoryMethod();
        // do something with the product
    }
}
