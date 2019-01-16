package com.wyb.test.design.behavioral.mediator;

/**
 * @author Kunzite
 * 中介者（Mediator）: 集中相关对象之间复杂的沟通和控制方式。
 * <p>
 * JDK
 * All scheduleXXX() methods of java.util.Timer
 * java.util.concurrent.Executor#execute()
 * submit() and invokeXXX() methods of java.util.concurrent.ExecutorService
 * scheduleXXX() methods of java.util.concurrent.ScheduledExecutorService
 * java.lang.reflect.Method#invoke()
 */
public class Client {

    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        CoffeePot coffeePot = new CoffeePot();
        Calender calender = new Calender();
        Sprinkler sprinkler = new Sprinkler();
        Mediator mediator = new ConcreteMediator(alarm, coffeePot, calender, sprinkler);
        // 闹钟事件到达，调用中介者就可以操作相关对象
        alarm.onEvent(mediator);
    }
}
