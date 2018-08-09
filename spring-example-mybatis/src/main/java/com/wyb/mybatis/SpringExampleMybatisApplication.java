package com.wyb.mybatis;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wyb.mybatis.dao.mapper")
@EnableAdminServer
public class SpringExampleMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleMybatisApplication.class, args);
	}
}
