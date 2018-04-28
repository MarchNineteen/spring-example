package com.wyb.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(value = {"com.wyb.shiro"})
@MapperScan(basePackages = "com.wyb.shiro.dao.mapper")
public class SpringExampleShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleShiroApplication.class, args);
	}
}
