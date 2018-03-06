package com.wyb.aop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = "com.wyb.aop.dao.mapper")
@ImportResource({"classpath*:spring/spring-*.xml"})
public class SpringExampleAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleAopApplication.class, args);
	}
}
