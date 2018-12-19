package com.wyb.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemory实战之堆溢出
 *
 * @author Kunzite
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
