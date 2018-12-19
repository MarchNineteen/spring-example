package com.wyb.test.design.adapter;

/**
 * 鸭子冒充了火鸡拥有火鸡的方法
 *
 * @author Kunzite
 */
public class DuckAdapter implements Turkey {

    Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gobble() {
        duck.quack();
    }
}
