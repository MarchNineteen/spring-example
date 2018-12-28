package com.wyb.test.design.create.singleton;


import java.util.HashMap;

/**
 * @author Kunzite
 * spring  单例注册表形式
 */
public class RegSingleton {

    private static HashMap registry = new HashMap();//静态变量 存放注册的单例

    //静态块，在类被加载时自动执行
    static {
        RegSingleton rs = new RegSingleton();
        registry.put(rs.getClass().getName(), rs);
    }

    //受保护的默认构造函数，如果为继承关系，则可以调用，克服了单例类不能为继承的缺点
    protected RegSingleton() {
    }

    public static RegSingleton getInstance(String name) {
        if (name == null) {
            name = "RegSingleton";
        }
        if (registry.get(name) == null) {
            try {
                registry.put(name, Class.forName(name).newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return (RegSingleton) registry.get(name);
    }
}
