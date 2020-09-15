package com.wyb.log.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcher丶
 */
public class Jcl {

    private Log logger = LogFactory.getLog(Jcl.class);

    public void test() {
        logger.info("Jcl {}");
    }

    public static void main(String[] args) {
        Jcl jcl = new Jcl();
        jcl.test();
    }
}
