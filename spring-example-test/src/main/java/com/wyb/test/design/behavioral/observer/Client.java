package com.wyb.test.design.behavioral.observer;

/**
 * @author Kunzite
 * 观察者（Observer）: 定义对象之间的一对多依赖，当一个对象状态改变时，它的所有依赖都会收到通知并且自动更新状态。
 * 主题（Subject）是被观察的对象，而其所有依赖者（Observer）称为观察者。
 * <p>
 * java.util.Observer
 * java.util.EventListener
 * javax.servlet.http.HttpSessionBindingListener
 * RxJava
 *
 * 主题依赖了观察者的一个集合，观察者依赖了一个主题，生成一个观察者需要在主题进行注册，主题更新后对每个观察者进行推送
 */
public class Client {

    public static void main(String[] args) {
        WeatherSubject subject = new WeatherSubject();
        Observer statis = new StatisticsDisplay(subject);
        Observer current = new CurrentConditionsDisplay(subject);
        subject.setMeasurements(22f, 29f, 100f);
    }
}
