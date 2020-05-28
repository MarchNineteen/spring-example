/*
 * @(#)ListTest    Created on 2019/3/18
 * Copyright (c) 2019 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.java.list;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyb
 * @version $$ Revision: 1.0 $$, $$ Date: 2019/3/18 12:33 $$
 */
public class ListTest {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList();
//        list.add(1);
//        list.add(2);
//
//        Field[] fields = list.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getGenericType());
//        }

        List<A> aList = new ArrayList<>();
        List<B> bs = new ArrayList<>();
//        bs.add(new B("b1"));
//        bs.add(new B("b2"));
        aList.add(new A("a", bs));
////        aList.add(new A("a"));
        System.out.println(aList.toString());
//        B b = null;
//        String s = "" + aList + b;
//        System.out.println(s);
    }

    static class A {
        private String a;
        private List<B> bList;

        public A(String a, List<B> bList) {
            this.a = a;
            this.bList = bList;
        }

        public A(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a='" + a + '\'' +
                    ", bList=" + bList +
                    '}';
        }
    }

    static class B {
        private String s;

        public B(String s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return "B{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }
}
