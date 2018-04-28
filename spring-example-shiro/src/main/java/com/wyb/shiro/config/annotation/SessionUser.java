package com.wyb.shiro.config.annotation;

import java.lang.annotation.*;

/**
 * 获取Shiro当前用户
 * @author Kunzite
 * @see SessionUserArgumentResolver
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionUser {
}
