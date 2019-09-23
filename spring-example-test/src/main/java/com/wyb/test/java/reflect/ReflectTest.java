package com.wyb.test.java.reflect;

import java.lang.reflect.*;

/**
 * 反射demo
 *
 * @author Kunzite
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //获取类class的4种方法
        Class<Person> personClazz1 = Person.class; // 1.直接获取
        Class<Person> personClazz2 = (Class<Person>) Class.forName("com.wyb.test.java.reflect.Person"); // 2.通过反正class类的forName获取 默认会执行初始化块
        Class<Person> personClazz3 = (Class<Person>) new Person("", "", "").getClass(); // 3.通过对象获得类模板
        Class<Person> personClazz4 = (Class<Person>) ClassLoader.getSystemClassLoader().loadClass("com.wyb.test.java.reflect.Person");// 4.使用ClassLoader.loadClass()来加载类，不会执行初始化块
        if (personClazz1 == personClazz2 && personClazz2 == personClazz3 && personClazz3 == personClazz4) {
            System.out.println("c1、c2、c3、c4 为同一个类对象");
        }

        //创建对象实例的方式
//        Person person1 = personClazz1.newInstance();// 1.class类直接newInstance  类必须有无参构造方法 否则抛出InstantiationException异常
        Constructor<Person> constructor = personClazz1.getConstructor(String.class, String.class, String.class);// 2.根据构造方法的参数类型获取构造器
        Person person2 = constructor.newInstance("2", "2", "2");
        Person person3 = constructor.newInstance(new Object[]{"3", "3", "3"});
        System.out.println(person2.age + "," + person2.name + "," + person2.getHobby());
        System.out.println(person3.age + "," + person3.name + "," + person3.getHobby());

        // 获取类的全部信息
        Class<Employee> employeeClass = (Class<Employee>) Class.forName("com.wyb.test.java.reflect.Employee", false, ClassLoader.getSystemClassLoader());// 通过该classfor 指定类是否加载static块,否则在newInstance时加载
        Class personClass5 = employeeClass.getSuperclass();// 获取父class
        /**
         * int getModifiers() 返回一个用于描述Field、Method和Constructor的修饰符的整形数值，该数值代表的含义可通过Modifier这个类分析
         * Modifier 类 它提供了有关Field、Method和Constructor等的访问修饰符的信息，主要的方法有：toString(int modifiers)返回整形数值modifiers代表的修饰符的字符串；
         * isAbstract是否被abstract修饰；isVolatile是否被volatile修饰；isPrivate是否为private；isProtected是否为protected；isPublic是否为public；isStatic是否为static修饰；
         * 等等，见名知义
         */
        String modifiers = Modifier.toString(employeeClass.getModifiers());
        if (modifiers.length() > 0) {
            System.out.println(modifiers + " ");
        }
        System.out.println("class " + employeeClass.getName());
        if (personClass5 != null && personClass5 != Object.class) {
            System.out.println(" extends " + personClass5.getName());// 打印父类
        }

        // 打印所有的构造方法
        printConstructors(employeeClass);
        printMethods(employeeClass);
        printFields(employeeClass);
    }

    /**
     * 打印Class对象的所有构造方法
     */
    public static void printConstructors(Class clazz) throws NoSuchMethodException {
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();//返回这个类的所有构造器的对象数组，包含保护和私有的构造器
//        Constructor[] constructors = clazz.getConstructors();//返回这个类的所有公有构造器的对象数组(包括父类超类)，不包含保护和私有的构造器
        for (Constructor c : declaredConstructors) {
            System.out.print(c.getName());
            System.out.print(" 构造方法 " + Modifier.toString(c.getModifiers()) + " ");
            //打印构造参数
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i]);
            }
        }
    }

    /**
     * 打印Class对象的所有方法
     */
    public static void printMethods(Class clazz) throws NoSuchMethodException {
        Method[] declaredMethods = clazz.getDeclaredMethods();//返回这个类或接口的所有方法，包括保护和私有的方法，不包括超类的方法
//        Method[] methods = clazz.getMethods();//返回这个类及其超类的公有方法的对象数组，不含保护和私有的方法
        for (Method m : declaredMethods) {
            System.out.println();
            System.out.print(" 修饰符 " + Modifier.toString(m.getModifiers()) + " ");
            System.out.print(" 返回类型 " + m.getReturnType() + " ");
            System.out.print(" 名称" + m.getName());
            //打印构造参数
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i]);
            }
        }
    }

    /**
     * 打印Class的所有属性
     */
    public static void printFields(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] fields = clazz.getFields();
        for (Field f: declaredFields) {
            Class type = f.getType();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length()> 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + f.getName() + ";");
        }
    }

//    public static void main(String[] args) {
//        try {
////            Class clazz = UserDo.class;
//            Class c = Class.forName("com.wyb.test.java.reflect.ReflectStudent");
////            ReflectStudent student = (ReflectStudent) c.newInstance();
////            if (student.getClass() == c) {
////                System.out.println(" T");
////            } else {
////                System.out.println("F");
////            }
//            Constructor[] constructors = c.getDeclaredConstructors();
//            Constructor constructor = c.getConstructor(String.class);
//            ReflectStudent student = (ReflectStudent) constructor.newInstance("122");
//            for (Constructor m : constructors) {
//                System.out.println(m.toString());
//            }
//            System.out.println("----------------------------------------------------------");
//            Method[] me = c.getDeclaredMethods();//获取的是类自身声明的所有方法，包含public、protected和private方法。
//            Method test = c.getMethod("test", String.class);
//            Method test2 = c.getMethod("test2", String.class, String.class);
//            System.out.println(test.invoke(student, "1"));
//            System.out.println(test2.invoke(student, "1", "2"));
//            for (Method m : me) {
//                if ("toString".equals(m.getName())) {
//                    System.out.println(m.invoke(student));
//                }
//                System.out.println(m.toString());
//            }
//
//
//            System.out.println("----------------------------------------------------------");
//            Method[] me2 = c.getMethods();//　获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
//            for (Method m : me2) {
//                System.out.println(m.toString());
//            }
//            System.out.println("----------------------------------------------------------");
//            Constructor[] constructors2 = c.getConstructors();
//            for (Constructor m : constructors2) {
//                System.out.println(m.toString());
//            }
//            System.out.println("----------------------------------------------------------");
//            student.getClass().getFields();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//    }
}
