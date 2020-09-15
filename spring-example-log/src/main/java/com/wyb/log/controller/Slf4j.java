package com.wyb.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcher丶
 */
public class Slf4j {

    private static Logger logger = LoggerFactory.getLogger("Slf4");

    public void test() {
        logger.info("Slf4j 默认使用 {}", ((ch.qos.logback.classic.Logger) logger).getLoggerContext().getClass().getName());

    }

    public static void main(String[] args) {
        Slf4j slf4j = new Slf4j();
        slf4j.test();
    }
}
