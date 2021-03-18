package com.wyb.mq.kafka.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wyb.mq.kafka.mq.KafkaProducer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Kunzite
 */
@RestController
@Api(value = "Kafka测试接口", tags = "KafkaController")
public class KafkaController {

    @Resource
    KafkaProducer kafkaProducer;

    @ApiOperation(value = "发送Kafka消息接口", notes = "发送Kafka消息接口", response = Object.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "发送消息", required = false, dataType = "String", paramType = "query") })
    @PostMapping(value = "Kafka/sendMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendMsg(String msg) {
        kafkaProducer.send("KafkaController 发送成功");
    }

}
