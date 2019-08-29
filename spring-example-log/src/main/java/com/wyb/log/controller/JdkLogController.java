package com.wyb.log.controller;

import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

/**
 * @author Marcher丶
 */
@Controller
public class JdkLogController {

    private static Logger logger = Logger.getLogger("JdkLogController");

    public void test() {
        logger.info("JdkLog");
    }
}
