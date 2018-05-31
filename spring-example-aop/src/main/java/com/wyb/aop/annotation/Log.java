package com.wyb.aop.annotation;

import java.lang.annotation.*;

/**
 * @author Kunzite
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Logs.class)
public @interface Log {

    /**
     * 记录类型
     *
     * @return
     */
    String value();
}
