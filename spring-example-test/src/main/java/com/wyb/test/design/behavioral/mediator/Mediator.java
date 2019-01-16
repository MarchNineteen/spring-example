package com.wyb.test.design.behavioral.mediator;

/**
 * @author Kunzite
 * 中介者接口用于与各同事（Colleague）对象通信。
 */
public abstract class Mediator {

    public abstract void doEvent(String eventType);

}
