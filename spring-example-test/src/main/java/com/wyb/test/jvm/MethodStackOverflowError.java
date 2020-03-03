package com.wyb.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcher丶
 * 1.7之后无效 -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodStackOverflowError {

    public static void main(String[] args) {
        // 使用list保持常量池引用，避免fullGc
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
