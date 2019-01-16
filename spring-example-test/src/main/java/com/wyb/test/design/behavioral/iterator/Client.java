package com.wyb.test.design.behavioral.iterator;

/**
 * @author Kunzite
 * 迭代器（Iterator）:提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示
 * <p>
 * JDK
 * java.util.Iterator
 * java.util.Enumeration
 */
public class Client {

    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        Iterator iteratorIterator = aggregate.createIterator();
        while (iteratorIterator.hasNext()) {
            System.out.println(iteratorIterator.next());
        }
    }
}
