package com.wyb.test.datastructure.adt.sequential;

/**
 * @author Kunzite
 * 排序顺序表
 */
public class SortedSeqList<T extends Comparable<? extends T>> extends SeqList<T> {

    public SortedSeqList()                 //构造空表
    {
        super();                                   //默认调用SeqList()
    }

    public SortedSeqList(int length) //构造空表，容量为length
    {
        super(length);                          //调用SeqList(length)
        //若省略，默认调用super()
    }

    public SortedSeqList(T[] values) //构造，由数组提供元素，
    {
        super(values.length);
        for (int i = 0; i < values.length; i++)
            this.insert(values[i]);         //插入元素
    }

}
