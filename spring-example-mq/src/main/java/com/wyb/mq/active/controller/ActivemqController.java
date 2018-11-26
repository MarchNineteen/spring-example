package com.wyb.mq.active.controller;

import com.wyb.mq.active.mq.ActiveSender;
import com.wyb.mq.active.mq.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@RestController
@Api(value = "ActiveMQ测试接口", tags = "ActivemqController")
public class ActivemqController {

    @Resource
    ActiveSender activeSender;

    @ApiOperation(value = "发送ActiveMQ消息接口", notes = "发送ActiveMQ消息接口", response = Object.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "发送消息", required = false, dataType = "String", paramType = "query")})
    @PostMapping(value = "active/sendMsg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object sendMsg(String msg) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setData(msg);
        messageDTO.setTopic("testMsg");
        activeSender.sender(messageDTO);
        return "发送成功" + messageDTO.toString();
    }

}
