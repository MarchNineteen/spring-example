package com.wyb.test.design.structure.bridge;

/**
 * @author Kunzite
 */
public class TclTv extends Tv {
    @Override
    public void on() {
        System.out.println("TclTv,on");
    }

    @Override
    public void off() {
        System.out.println("TclTv,off");
    }

    @Override
    public void tuneTunnel() {
        System.out.println("TclTv,tuneTunnel");
    }
}
