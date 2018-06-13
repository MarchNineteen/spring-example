package com.wyb.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = "com.wyb.shiro.dao.mapper")
public class SpringExampleShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleShiroApplication.class, args);
	}
}
