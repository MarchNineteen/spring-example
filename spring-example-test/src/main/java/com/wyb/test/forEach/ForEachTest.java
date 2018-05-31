package com.wyb.test.forEach;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * for循环解析
 *
 * @author Kunzite
 */
public class ForEachTest {

    private static ArrayList<String> arrayList = new ArrayList<>();

    static {
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add(null);// array可以为空
    }

    // 普通的for循环遍历
    public void testOne() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + "\n");
        }
    }

    // 迭代器进行遍历
    public void testTwo() {
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
    }

    /**
     * 增强for循环（for each）
     */
    public void testThree() {
        for (String s : arrayList) {
            System.out.println(s + ",");
        }
        /**
         * 反编译后 JAVA中的增强for循环底层是通过迭代器模式来实现的。
         */
//        Integer i;
//        for (Iterator iterator = arrayList.iterator(); iterator.hasNext(); System.out.println(i)) {
//            i = (Integer) iterator.next();
//        }
    }

    public void errorRemove() {
        for (String s : arrayList) {
            System.out.println(s + ",");
            //Java中有fail-fast机制。在使用迭代器遍历元素的时候，在对集合进行删除的时候一定要注意，使用不当有可能发生ConcurrentModificationException，这是一种运行时异常，编译期并不会发生。只有在程序真正运行时才会爆发。
            arrayList.remove(s);
        }
    }

    public void correctRemove() {
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            // 注意一个while里 只能使用一次iterator.next() 代表当前数据 再使用一次即变为下一个值
            String s1 = iterator.next();
//            String s2 = iterator.next();
            System.out.print(s1 + "\n");
//            System.out.print(s2 + "\n");
            if (s1.equals("1")) {
                iterator.remove();
            }
        }
        for (String s : arrayList) {
            System.out.println(s + "\n");
        }
    }

    public static void main(String[] args) {
        ForEachTest test = new ForEachTest();
//        test.testOne();
//        test.testTwo();
        test.testThree();
//        test.correctRemove();
    }
}
