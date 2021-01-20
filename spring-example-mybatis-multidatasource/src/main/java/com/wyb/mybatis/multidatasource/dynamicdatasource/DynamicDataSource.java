package com.wyb.mybatis.multidatasource.dynamicdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Marcher丶
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 核心方法，切换数据源上下文
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
