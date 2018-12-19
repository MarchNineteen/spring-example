package com.wyb.test.design.bridge;

/**
 * @author Kunzite
 */
public class SonyRemoteControl extends RemoteControl {

    public SonyRemoteControl(Tv tv) {
        super(tv);
    }

    @Override
    public void on() {
        System.out.println("sonyRemoteControl,on");
        tv.on();
    }

    @Override
    public void off() {
        System.out.println("sonyRemoteControl,off");
        tv.off();
    }

    @Override
    public void tuneTunnel() {
        System.out.println("sonyRemoteControl,tuneTunnel");
        tv.off();
    }
}
