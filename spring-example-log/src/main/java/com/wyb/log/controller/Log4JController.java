package com.wyb.log.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * @author Marcher丶
 */
@Controller
public class Log4JController {

    private static Logger logger = LogManager.getLogger("Log4j");

    public void test() {
        logger.info("log4j");
    }
}
