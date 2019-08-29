package com.wyb.log;

import com.wyb.log.controller.JdkLogController;
import com.wyb.log.controller.Log4J2Controller;
import com.wyb.log.controller.Log4JController;
import com.wyb.log.controller.LogBackController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SpringExampleLogApplication {

//    private static Logger logger = LoggerFactory.getLogger(SpringExampleLogApplication.class);
//    private static Logger logger = LoggerFactory.getLogger(SpringExampleLogApplication.class);

//    log4j2
    private static Logger logger = LogManager.getLogger(SpringExampleLogApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringExampleLogApplication.class, args);
        logger.info("ss {}", "11");
        JdkLogController jdkLog = context.getBean("jdkLogController", JdkLogController.class);
        jdkLog.test();
        Log4JController log4j = context.getBean("log4JController", Log4JController.class);
        log4j.test();
        Log4J2Controller log4j2 = context.getBean("log4J2Controller", Log4J2Controller.class);
        log4j2.test();
        LogBackController LogBack = context.getBean("logBackController", LogBackController.class);
        LogBack.test();
    }

}
