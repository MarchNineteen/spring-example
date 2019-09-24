package com.wyb.freemarker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wyb.freemarker.dao.mapper")
public class SpringExampleFreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringExampleFreemarkerApplication.class, args);
    }
}
