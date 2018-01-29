package com.wyb.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date:
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.wyb.business.dao.mapper")
@ImportResource({"classpath*:spring/spring-mybatis-*.xml"})
public class SpringExampleBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleBusinessApplication.class, args);
	}
}
