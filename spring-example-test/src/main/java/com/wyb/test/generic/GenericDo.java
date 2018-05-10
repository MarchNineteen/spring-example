package com.wyb.test.generic;

/**
 * @author Kunzite
 */
public class GenericDo<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {

        GenericDo<String> name = new GenericDo<>();



        System.out.println(name.getData());
    }

}
