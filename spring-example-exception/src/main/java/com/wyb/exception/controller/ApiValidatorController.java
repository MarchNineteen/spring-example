package com.wyb.exception.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.exception.entity.ValidatedEntity;
import com.wyb.exception.result.WebResult;
import com.wyb.exception.result.WebResultEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Marcher丶
 */
@Slf4j
@RestController
public class ApiValidatorController {

    @RequestMapping("/test")
    public WebResult test(@Validated @RequestBody ValidatedEntity entity) {
        return new WebResult(WebResultEnum.SUCCESS);
    }

}
