package com.wyb.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Kunzite
 */
@EnableCaching
@SpringBootApplication
public class SpringExampleCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleCacheApplication.class, args);
	}
}
