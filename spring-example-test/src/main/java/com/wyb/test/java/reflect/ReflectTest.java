package com.wyb.test.java.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射demo
 *
 * @author Kunzite
 */
public class ReflectTest {

    public static void main(String[] args) {

        Class clazz = UserDo.class;
        Class c = null;
        try {
            c = Class.forName("com.wyb.test.java.reflect.UserDo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can not class for userDo " + e);
        }
        UserDo userDo = null;
        try {
            userDo = (UserDo) c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(" Can not instance user" + e);
        }

        if (userDo.getClass() == c) {
            System.out.println(" T");
        } else {
            System.out.println("F");
        }

        Method[] me = c.getDeclaredMethods();//获取的是类自身声明的所有方法，包含public、protected和private方法。
        for (Method m : me) {
            if ("toString".equals(m.getName())) {
                try {
                    m.invoke(userDo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(m.toString());
        }
        System.out.println("----------------------------------------------------------");
        Method[] me2 = c.getMethods();//　获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
        for (Method m : me2) {
            System.out.println(m.toString());
        }

        userDo.getClass().getFields();
    }

    ;


}
