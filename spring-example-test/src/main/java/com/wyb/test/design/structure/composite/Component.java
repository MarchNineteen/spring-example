package com.wyb.test.design.structure.composite;

/**
 * @author Kunzite
 */
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public void print() {
        print(0);
    }

    public abstract void print(int level);

    public abstract void add(Component component);

    public abstract void remove(Component component);

}
