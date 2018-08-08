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
        try {
            Class clazz = UserDo.class;
            Class c = null;
            c = Class.forName("com.wyb.test.java.reflect.UserDo");
            UserDo userDo = null;
            userDo = (UserDo) c.newInstance();

            if (userDo.getClass() == c) {
                System.out.println(" T");
            } else {
                System.out.println("F");
            }

            Method[] me = c.getDeclaredMethods();//获取的是类自身声明的所有方法，包含public、protected和private方法。
            for (Method m : me) {
                if ("toString".equals(m.getName())) {
                    m.invoke(userDo);
                }
                System.out.println(m.toString());
            }
            System.out.println("----------------------------------------------------------");
            Method[] me2 = c.getMethods();//　获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
            for (Method m : me2) {
                System.out.println(m.toString());
            }

            userDo.getClass().getFields();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
