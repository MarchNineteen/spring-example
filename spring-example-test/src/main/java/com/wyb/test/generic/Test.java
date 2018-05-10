package com.wyb.test.generic;

/**
 * @author Kunzite
 */
public class Test {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        b.setB("这是 B 的Strinng");
        a.setA(" 这是A 的 String");
        a.setB(b);
        B B1 = a.getB();
        B1.setB("A Stirng");
        System.out.println(a.toString());

    }
}

class A{
    String a;
    private B b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                ", b=" + b +
                '}';
    }
}

class B{
    String b;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b='" + b + '\'' +
                '}';
    }
}