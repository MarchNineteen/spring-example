package com.wyb.test.design.create.simpleFactory;

/**
 * @author Kunzite
 */
public class ProductFactory {

    public Product createProduct(int type) {
        Product product = null;
        if (1 == type) {
            product = new ProductOneImpl();
        }
        if (2 == type) {
            product = new ProductTwoImpl();
        }
        return product;
    }

    /**
     * 具体子类具体实现无需告诉client，实现解耦
     * @param args
     */
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        Product product = factory.createProduct(1);
    }

}



