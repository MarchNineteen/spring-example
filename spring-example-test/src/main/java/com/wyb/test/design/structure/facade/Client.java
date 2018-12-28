package com.wyb.test.design.structure.facade;

/**
 * @author Kunzite
 * 外观模式 提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。
 */
public class Client {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.watchMovie("家有喜事");
    }
}
