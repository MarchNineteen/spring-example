package com.wyb.log.controller;


import java.util.logging.Logger;

/**
 * @author Marcher丶
 */
public class JdkLog {

    private static Logger logger = Logger.getLogger("JdkLogController");

    public void test() {
        logger.info("JdkLog");
    }

    public static void main(String[] args) {
        JdkLog controller = new JdkLog();
        controller.test();
    }
}
