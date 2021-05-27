package com.wyb.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Kunzite
 */
@EnableCaching
@SpringBootApplication(exclude = CacheAutoConfiguration.class)
@MapperScan(basePackages = "com.wyb.cache.dao.mapper")
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
