package com.wyb.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcher丶
 */
public class LogBack {

    private static Logger logger = LoggerFactory.getLogger("LogBackController");

    public void test() {
        logger.info("LogBack 需要配合slf4使用");
    }

    public static void main(String[] args) {
        LogBack controller = new LogBack();
        controller.test();
    }
}
