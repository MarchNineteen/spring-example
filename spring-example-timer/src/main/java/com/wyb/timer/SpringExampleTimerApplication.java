package com.wyb.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.wyb.timer.dao.mapper")
@ImportResource({"classpath*:spring/spring-*.xml"})
//@EnableScheduling
public class SpringExampleTimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringExampleTimerApplication.class, args);
    }
}
