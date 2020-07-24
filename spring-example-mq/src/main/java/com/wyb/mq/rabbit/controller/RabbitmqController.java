package com.wyb.mq.rabbit.controller;

import com.wyb.mq.rabbit.constants.RabbitConstants;
import com.wyb.mq.rabbit.mq.SendMessage;
import com.wyb.mq.rabbit.mq.sender.RabbitSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kunzite
 */
@RestController
@Api(value = "MQ测试接口", tags = "RabbitmqController")
public class RabbitmqController {

    @Autowired
    private RabbitSender rabbitSender;

    @ApiOperation(value = "发送MQ消息接口", notes = "用户名", response = Object.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query")})
    @PostMapping(value = "sendMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object sendMsg(String name) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setId("1");
        sendMessage.setAge(20);
        sendMessage.setName(name);

        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_SEND_AWARD, RabbitConstants.MQ_ROUTING_KEY_SEND_COUPON, sendMessage);
        return "发送成功";
    }

    @ApiOperation(value = "发送延时消息", notes = "内容", response = Object.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = false, dataType = "String", paramType = "query")})
    @PostMapping(value = "sendDelayMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object sendDelayMsg(String name) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setName(StringUtils.isEmpty(name) ? "消息将在5s后发送到延迟队列" : name);

        rabbitSender.sendMessage(RabbitConstants.MQ_EXCHANGE_DELAY_QUEUE, RabbitConstants.MQ_ROUTING_KEY_DELAY_QUEUE, sendMessage);
        return "发送成功";
    }

}
