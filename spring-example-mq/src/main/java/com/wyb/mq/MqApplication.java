package com.wyb.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication()
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }
}
