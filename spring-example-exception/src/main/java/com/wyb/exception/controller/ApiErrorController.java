package com.wyb.exception.controller;

import com.wyb.exception.result.WebResult;
import com.wyb.exception.result.WebResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ErrorController
 *
 * @author Marcher丶
 */
@Slf4j
@RestController
public class ApiErrorController implements ErrorController {

    @RequestMapping("/error")
    public WebResult apiError(Exception e) {
        WebResult response = new WebResult();
        response.setResultCode(WebResultEnum.SYSTEM_FAILURE.getCode());
        response.setMessage("Internal server error: " + e.getMessage());
        // send metric
        return response;
    }

    @Override
    public String getErrorPath() {
        return "/error11";
    }
}
