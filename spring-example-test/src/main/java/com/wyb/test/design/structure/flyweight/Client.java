package com.wyb.test.design.structure.flyweight;

/**
 * @author Kunzite
 * 享元（Flyweight）:利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的。
 * JDK
 * Java 利用缓存来加速大量小对象的访问时间。
 * java.lang.Integer#valueOf(int)
 * java.lang.Boolean#valueOf(boolean)
 * java.lang.Byte#valueOf(byte)
 * java.lang.Character#valueOf(char)
 */
public class Client {

    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight1 = factory.getFlyweight("aa");
        Flyweight flyweight2 = factory.getFlyweight("aa");
        flyweight1.extrinsicState("x");
        flyweight2.extrinsicState("y");
    }
}
