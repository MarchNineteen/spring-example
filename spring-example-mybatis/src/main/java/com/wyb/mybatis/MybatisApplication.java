package com.wyb.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.wyb.mybatis.dao.mapper")
@EnableAdminServer
@Slf4j
@EnableTransactionManagement
public class MybatisApplication implements CommandLineRunner, ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger("printByNameLog");

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MybatisApplication.class, args);
        logger.error("指定日志名称输出到文件：{}", args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 执行特定的代码
        log.info("CommandLineRunner run方法参数列表：{}", args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner run方法参数列表：{}", args);
    }
}
