package com.wyb.mybatis.multidatasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Marcher丶
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource.master") // 注意这个前缀要和application.yml文件的前缀一样
@Component
public class MasterConfig {
    // @Value("${mysql.datasource.master.jdbcurl}")
    //@Value("${jdbcurl}")
    private String url;
    //private String url;
    // 比如这个url在properties中是这样子的mysql.datasource.master.username = root
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
