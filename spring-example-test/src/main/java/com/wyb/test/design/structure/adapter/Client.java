package com.wyb.test.design.structure.adapter;

/**
 * @author Kunzite
 */
public class Client {

    public static void main(String[] args) {
        Duck duck = new WildDuck();
        Turkey turkey = new DuckAdapter(duck);
        turkey.gobble();
    }
}
