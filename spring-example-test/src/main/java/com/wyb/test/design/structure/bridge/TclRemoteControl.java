package com.wyb.test.design.structure.bridge;

/**
 * @author Kunzite
 */
public class TclRemoteControl extends RemoteControl {

    public TclRemoteControl(Tv tv) {
        super(tv);
    }
    @Override
    public void on() {
        System.out.println("TclRemoteControl,on");
        tv.on();
    }

    @Override
    public void off() {
        System.out.println("TclRemoteControl,off");
        tv.off();
    }

    @Override
    public void tuneTunnel() {
        System.out.println("TclRemoteControl,tuneTunnel");
        tv.tuneTunnel();
    }
}
