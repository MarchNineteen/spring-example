package com.wyb.test.design.adapter;

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
