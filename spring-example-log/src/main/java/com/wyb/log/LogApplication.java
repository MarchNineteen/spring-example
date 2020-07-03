package com.wyb.log;

//import com.wyb.log.controller.JdkLogController;
//import com.wyb.log.controller.Log4J2Controller;
//import com.wyb.log.controller.Log4JController;
//import com.wyb.log.controller.LogBackController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class LogApplication {

//    private static Logger jdkLogger = Logger.getLogger("SpringExampleLogApplication.class");
//    log4j
//    private static Logger log4jLogger = LogManager.getLogger(SpringExampleLogApplication.class);
//    log4j2
    private static Logger log4j2Logger = LogManager.getLogger(LogApplication.class);
//    logback
//    private static Logger logbackLogger = LoggerFactory.getLogger(SpringExampleLogApplication.class);
//    jcl
//    private static Log jclLogger = LogFactory.getLog(SpringExampleLogApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LogApplication.class, args);
//        jdkLogger.info("jdk log ");
//        log4jLogger.info("log4j log ");
        log4j2Logger.info("log4j2 log ", "11");
//        logbackLogger.info("logback log ", "11");
//        jclLogger.info("jcl log ");

//        JdkLogController jdkLog = context.getBean("jdkLogController", JdkLogController.class);
//        jdkLog.test();
//        Log4JController log4j = context.getBean("log4JController", Log4JController.class);
//        log4j.test();
//        Log4J2Controller log4j2 = context.getBean("log4J2Controller", Log4J2Controller.class);
//        log4j2.test();
//        LogBackController LogBack = context.getBean("logBackController", LogBackController.class);
//        LogBack.test();
    }

}
