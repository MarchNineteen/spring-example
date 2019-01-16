package com.wyb.test.design.behavioral.observer;

/**
 * @author Kunzite
 */
public interface Subject {

    // 注册观察者
    public void registerObserver(Observer observer);

    // 移除观察者
    public void removeObserver(Observer observer);

    // 提醒观察者
    public void notifyObserver();
}
