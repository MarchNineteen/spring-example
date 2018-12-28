package com.wyb.test.design.structure.bridge;

/**
 * @author Kunzite
 */
public abstract class RemoteControl {

    protected Tv tv;

    public RemoteControl(Tv tv) {
        this.tv = tv;
    }

    public abstract void on();

    public abstract void off();

    public abstract void tuneTunnel();

}
