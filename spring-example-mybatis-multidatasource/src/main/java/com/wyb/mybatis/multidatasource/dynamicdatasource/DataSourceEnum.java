package com.wyb.mybatis.multidatasource.dynamicdatasource;

/**
 * @author Marcher丶
 */
public enum DataSourceEnum {

    /**
     * 主库
     */
    MASTER("主库"),

    SLAVE("从库");

    private String dataSourceName;

    private DataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}
