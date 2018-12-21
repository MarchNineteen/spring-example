package com.wyb.test.java.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author Kunzite
 */
public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (visited.contains(obj)) {
            return "...";
        }
        visited.add(obj);

        Class clazz = obj.getClass();
        // string
        if (String.class == clazz) {
            return (String) obj;
        }
        // 数组 打印数组里的类型 如果是基础类型直接输出 不是的话说明是类 递归调用toString
        if (clazz.isArray()) {
            String r = clazz.getComponentType() + "[]{\n";     // 数组的元素的类型
            Class types = clazz.getComponentType();//返回数组类里组件类型的 Class，如果不是数组类则返回null
            for (int i = 0;i < Array.getLength(obj); i++) {
                if (i > 0) {   // 不是数组的第一个元素加逗号和换行，显示更加美观
                    r += ",\n";
                }
                r += "\t";
                Object o = Array.get(obj, i);
                // 基础类型直接输出
                if (o.getClass().isPrimitive()) {
                    r += "\t";
                } else {
                    r += toString(o);
                }
            }
            return r + "\n}";
        }
        // 既不是String，也不是数组时，输出该对象的类型和属性值
        String r = clazz.getName();
        do {
            r += "[";
            Field[] fields = clazz.getDeclaredFields();    // 获取该类自己定义的所有域，包括私有的，不包括父类的
            AccessibleObject.setAccessible(fields, true); // 访问私有的属性，需要打开这个设置，否则会报非法访问异常
            for (Field f : fields) {
                if (!Modifier.isStatic(f.getModifiers())) { // 通过 Modifier 可获取该域的修饰符，这里判断是否为 static
                    if (!r.endsWith("[")) {
                        r += ",";
                    }
                    r += f.getName() + "=";     // 域名称
                    try {
                        Class t = f.getType();  // 域（属性）的类型
                        Object val = f.get(obj);   // 获取obj对象上该域的实际值
                        if (t.isPrimitive()) {     // 如果类型为8种基本类型，则直接输出
                            r += val;
                        } else {
                            r += toString(val);     // 不是8种基本类型，递归调用toString
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            clazz = clazz.getSuperclass(); // 继续打印超类的类信息
        } while (clazz != null);
        return r;
    }

    public static void main(String[] args) {
        ObjectAnalyzer analyzer = new ObjectAnalyzer();
        Employee employee = new Employee("小明", "18", "爱好写代码", 1, "Java攻城狮", 100); // 分析自定义类Employee的对象的实际值
        System.out.println(analyzer.toString(new Object[] { 1,"11", employee}));
    }
}
