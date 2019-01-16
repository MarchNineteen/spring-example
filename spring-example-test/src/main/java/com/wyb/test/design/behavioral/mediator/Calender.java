package com.wyb.test.design.behavioral.mediator;

/**
 * @author Kunzite
 */
public class Calender extends Colleague {

    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("calender");
    }

    public void doCalender() {
        System.out.println("doCalender()");
    }
}
