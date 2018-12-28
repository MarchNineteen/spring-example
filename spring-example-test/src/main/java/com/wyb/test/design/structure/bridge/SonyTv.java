package com.wyb.test.design.structure.bridge;

/**
 * @author Kunzite
 */
public class SonyTv extends Tv {

    @Override
    public void on() {
        System.out.println("SonyTv,on");
    }

    @Override
    public void off() {
        System.out.println("SonyTv,off");
    }

    @Override
    public void tuneTunnel() {
        System.out.println("SonyTv,tuneTunnel");
    }
}
