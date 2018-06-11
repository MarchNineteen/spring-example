package com.wyb.shiro.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author Kunzite
 */
public class BeanUtil {

    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T o = null;
        try {
            o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, o);
        return o;
    }
}
