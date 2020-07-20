/*
 * @(#)Test    Created on 2020/3/24
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.thread.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/3/24 9:44 $$
 */
public class Test {

    private String[] arr = new String[100];
    private List<String> arrList = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();
    Condition aCondition = lock.newCondition();
    Condition LCondition = lock.newCondition();
    Condition ICondition = lock.newCondition();

    private boolean flagA = true;
    private boolean flagL = false;
    private boolean flagI = false;

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(test::addA).start();
        new Thread(test::addL).start();
        new Thread(test::addI).start();
    }

    private void addA() {
        lock.lock();
        try {
            for (int i = 0; i < 2; i++) {
                while (!flagA) {
                    try {
                        aCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("addA : a");

//                arrList.add("a");
//                arrList.forEach(System.out::println);
                flagA = false;
                flagL = true;
                flagI = false;
                LCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    private void addL() {
        lock.lock();
        try {
            while (true) {
                while (!flagL) {
                    try {
                        LCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("addL : l");
//                arrList.add("l");
//                arrList.forEach( v -> System.out.println("addL : l"));
                flagL = false;
                flagI = true;
                flagA = false;
                ICondition.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    private void addI() {
        lock.lock();
        try {
            while (true) {
                while (!flagI) {
                    try {
                        ICondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("addI : i");

//                arrList.add("i");
//                arrList.forEach(System.out::println);
                flagI = false;
                flagA = true;
                flagL = false;
                aCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
