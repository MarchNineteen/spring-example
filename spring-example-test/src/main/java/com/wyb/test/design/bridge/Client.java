package com.wyb.test.design.bridge;

/**
 * RemoteControl 表示遥控器，指代 Abstraction。
 * <p>
 * TV 表示电视，指代 Implementor。
 * 桥接模式将遥控器和电视分离开来，从而可以独立改变遥控器或者电视的实现。
 * <p>
 *
 * @author Kunzite
 */
public class Client {

    public static void main(String[] args) {
        Tv sonyTv = new SonyTv();
        SonyRemoteControl sonyRemoteControl = new SonyRemoteControl(sonyTv);
        sonyRemoteControl.on();
        sonyRemoteControl.off();
        sonyRemoteControl.tuneTunnel();

        Tv tclTv = new TclTv();
        TclRemoteControl tclRemoteControl = new TclRemoteControl(tclTv);
        tclRemoteControl.on();
        tclRemoteControl.off();
        tclRemoteControl.tuneTunnel();
    }
}
