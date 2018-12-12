package com.wyb.test.design.prototype;

/**
 * @author wangyb
 */
public class Client {

    public static void main(String[] args) {
        Prototype myPrototype = new MyPrototype("111");
        Prototype cloneProtoType = myPrototype.myClone();
        System.out.println(cloneProtoType.toString());
    }
}
