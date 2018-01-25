package com.wyb.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author: Kunzite
 * @Date:
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.wyb.business.dao.mapper")
public class SpringExampleBusinessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleBusinessApplication.class, args);
	}
}
