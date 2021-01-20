package com.wyb.mybatis.multidatasource.dynamicdatasource;

import java.lang.annotation.*;

/**
 * @author Marcher丶
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSwitcher {

    /**
     * 默认数据源
     *
     * @return
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;

    /**
     * 清除
     *
     * @return
     */
    boolean clear() default true;
}
