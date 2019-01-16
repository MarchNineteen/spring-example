package com.wyb.test.design.behavioral.command;

/**
 * @author Kunzite
 * @version $$ Revision: 1.0 $$, $$ Date: 2018/12/28 16:43 $$
 */
public class Light {

    private Boolean light;// 灯的状态

    public void turnOn() {
        light = true;
    }

    public void turnOff() {
        light = false;
    }

    public Boolean getLight() {
        return light;
    }

    public void setLight(Boolean light) {
        this.light = light;
    }
}
