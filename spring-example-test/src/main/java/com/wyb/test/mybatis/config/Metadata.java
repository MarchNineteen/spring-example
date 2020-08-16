package com.wyb.test.mybatis.config;

/**
 * @author Marcher丶
 * mapper 类信息
 */
public class Metadata {
    private String name;
    private Class<?> clazz;

    public Metadata(Class<?> clazz) {
        name = clazz.getSimpleName();
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
