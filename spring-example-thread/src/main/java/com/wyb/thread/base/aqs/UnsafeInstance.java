/*
 * @(#)UnsafeInstance    Created on 2021/5/26
 * Copyright (c) 2021 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.thread.base.aqs;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2021/5/26 17:14 $$
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
