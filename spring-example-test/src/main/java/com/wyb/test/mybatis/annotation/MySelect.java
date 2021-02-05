package com.wyb.test.mybatis.annotation;

import java.lang.annotation.*;

/**
 * @author Marcher丶
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MySelect {

    /**
     * sql 语句
     */
    String value();
}
