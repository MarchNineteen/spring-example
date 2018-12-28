package com.wyb.test.design.structure.adapter;

/**
 * @author Kunzite
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("火鸡叫");
    }
}
