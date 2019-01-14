package com.wyb.test.datastructure.adt;

import java.util.List;

/**
 * @author Kunzite
 * 线性表抽象数据类型，数据元素的数据类型为T
 */
public interface Adt<T> {

    boolean isEmpty();// 判断是否为空

    int size();// 线性表的长度

    T get(int i);// 获取第i个元素

    void set(int i, T t);// 设置第i个元素

    void insert(T t);// 在表尾部插入

    void insert(int i, T t);// 在第i个位置插入

    T remove(int i);// 删除第i个元素

    void clear();// 清空表

    int search(T key);// 查找与key相等元素

    @Override
    boolean equals(Object obj);// 比较是否相等

    void add(List<T> list);//添加list所有元素

    @Override
    String toString();//所有元素的描述字符串
}
