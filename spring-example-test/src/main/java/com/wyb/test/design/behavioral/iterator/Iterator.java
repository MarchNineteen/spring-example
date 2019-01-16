package com.wyb.test.design.behavioral.iterator;

/**
 * @author Kunzite
 * 迭代器操作接口
 */
public interface Iterator<Item> {

    Item next();

    boolean hasNext();
}
