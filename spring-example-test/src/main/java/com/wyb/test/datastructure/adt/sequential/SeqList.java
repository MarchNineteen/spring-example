package com.wyb.test.datastructure.adt.sequential;

import com.wyb.test.datastructure.adt.Adt;

import java.util.List;

/**
 * @author Kunzite
 * 线性表的顺序存储和实现——顺序表类
 * <p>
 * 顺序表的静态特性很好，动态特性很差，具体说明如下。
 * ① 顺序表元素的物理存储顺序直接反映线性表元素的逻辑顺序，顺序表是一种随机存取结构。get()、set()方法时间复杂度是 O(1)。
 * ② 插入和删除操作效率很低。如果在各位置插入元素的概率相同，则有O(n)
 */
public class SeqList<T> implements Adt<T> {

    protected T[] element;  //对象数组
    protected int n;  //元素个数（长度）

    public SeqList(int length) {
    }

    //构造空表
    public SeqList() {
        this(64); //调用本类指定参数列表的构造方法
    }

    public SeqList(T[] values) { //由values数组提供元素
        element = values;
    }


    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public T get(int i) {
        checkRangeForAdd(i);
        return element[i];
    }

    @Override
    public void set(int i, T o) {
        checkRangeForAdd(i);
        element[i] = o;
    }

    @Override
    public void insert(Object o) {
    }

    @Override
    public void insert(int i, Object o) {
    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int search(Object key) {
        return 0;
    }

    @Override
    public void add(List list) {

    }

    // 比较是否相等
    @Override
    public boolean equals(Object obj) {
        if (this == obj)                                  //引用同一个实例
            return true;
        if (obj instanceof SeqList<?>)       //若obj引用顺序表实例。
        //SeqList<?>是所有SeqList<T>的父类
        {
            SeqList<T> list = (SeqList<T>) obj;
            if (this.n == list.n)                          //比较长度
            {
                for (int i = 0; i < this.n; i++)       //比较所有元素
                    if (!(this.get(i).equals(list.get(i))))  //运行时多态
                        return false;
                return true;
            }
        }
        return false;
    }

    private void checkRangeForAdd(int index) {
        if (index > n || index < 0) System.out.println("异常，数组越界");
    }
}
