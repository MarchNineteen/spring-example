package com.wyb.mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringExampleMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleMqApplication.class, args);
	}
}
