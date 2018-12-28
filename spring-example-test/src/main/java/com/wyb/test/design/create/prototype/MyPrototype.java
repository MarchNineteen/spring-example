package com.wyb.test.design.create.prototype;

/**
 * @author Kunzite
 */
public class MyPrototype extends Prototype {

    private String field;

    public MyPrototype(String field) {
        this.field = field;
    }

    @Override
    public Prototype myClone() {
        return new MyPrototype(field);
    }

    @Override
    public String toString() {
        return field;
    }
}
