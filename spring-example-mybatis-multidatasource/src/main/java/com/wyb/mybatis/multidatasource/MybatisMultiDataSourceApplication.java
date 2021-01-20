package com.wyb.mybatis.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Marcher丶
 */
@SpringBootApplication
public class MybatisMultiDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiDataSourceApplication.class, args);
    }

}
