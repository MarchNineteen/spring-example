package com.wyb.test.design.create.singleton;

/**
 * @author Kunzite
 */
public enum Enumer {

    SINGLETON;

    private String objName;

    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }


    public static void main(String[] args) {
        Enumer singleton1 = Enumer.SINGLETON;
        singleton1.setObjName("111");
        System.out.println(singleton1.getObjName());
        Enumer singleton2 = Enumer.SINGLETON;
        singleton2.setObjName("222");
        System.out.println(singleton1.getObjName());
        System.out.println(singleton2.getObjName());

        Enumer[] enumers = Enumer.class.getEnumConstants();
        for (Enumer enumConstant : enumers) {
            System.out.println(enumConstant.getObjName());
        }
    }
}
