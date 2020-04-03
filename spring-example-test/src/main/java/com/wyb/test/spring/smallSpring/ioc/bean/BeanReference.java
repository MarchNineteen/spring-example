package com.wyb.test.spring.smallSpring.ioc.bean;

/**
 * @author Marcher丶
 * 会放入property
 */
public class BeanReference {

    private String name;

    private Object reference;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }
}
