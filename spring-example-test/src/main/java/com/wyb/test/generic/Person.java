package com.wyb.test.generic;

/**
 * @author Kunzite
 */
public class Person<T> {

    private String sex;
    private String name;
    private T des;

    public Person() {
    }

    public Person(String sex, String name, T des) {
        this.sex = sex;
        this.name = name;
        this.des = des;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getDes() {
        return des;
    }

    public void setDes(T des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", des=" + des +
                '}';
    }
}
