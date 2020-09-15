package com.wyb.log.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Marcher丶
 */
public class Log4J2 {

    private Logger logger = LogManager.getLogger(Log4J2.class);

    public void test() {
        logger.info("log4j {}", "占位符");
    }

    public static void main(String[] args) {
        Log4J2 controller = new Log4J2();
        controller.test();
    }
}
