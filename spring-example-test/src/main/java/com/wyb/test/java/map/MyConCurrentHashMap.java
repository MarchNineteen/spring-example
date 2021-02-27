package com.wyb.test.java.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Marcher丶
 */
public class MyConCurrentHashMap {

    public static void main(String[] args) {

        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put("1", "1");

        Thread t1 = new Thread(() -> {
            System.out.println(chm.get("1"));

        });
        t1.start();

        Thread t2 = new Thread(() -> {
            chm.put("1", "2");
        });
        t2.start();
//        chm.put();
//        chm.size();
//        HashMap map = new HashMap();
//        map.size()
    }
}

