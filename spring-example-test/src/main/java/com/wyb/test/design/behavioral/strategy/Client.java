package com.wyb.test.design.behavioral.strategy;

/**
 * @author Kunzite
 * 策略（Strategy）: 定义一系列算法，封装每个算法，并使它们可以互换。策略模式可以让算法独立于使用它的客户端。
 * <p>
 * JDK
 * java.util.Comparator#compare()
 * javax.servlet.http.HttpServlet
 * javax.servlet.Filter#doFilter()
 */
public class Client {

    public static void main(String[] args) {
        Duck duck = new Duck();
        CallBehavior quack = new Quack();
        duck.setQuackBehavior(quack);
        duck.performQuack();
        CallBehavior squeak = new Squeak();
        duck.setQuackBehavior(squeak);
        duck.performQuack();
    }
}
