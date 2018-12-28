package com.wyb.test.design.create.abstractFactory;

/**
 * @author Kunzite
 */
public class Client {

    public static void main(String[] args) {
        ProductAFactory factoryA = new ProductAFactory();
        AbstractProductA productA1 = factoryA.createProductA();
        AbstractProductB productB1 = factoryA.createProductB();
    }
}
