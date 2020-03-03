package com.wyb.test.jvm;

/**
 * @author Marcher丶
 * -Xss5m 设置最大调用深度
 */
public class StackAreaStackOverflowError {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackAreaStackOverflowError sof = new StackAreaStackOverflowError();
        try {
            sof.stackLeak();
        } catch (java.lang.StackOverflowError error) {
            System.out.println("请求的栈深度为：" + sof.stackLength);
            throw error;
        }
    }
}
