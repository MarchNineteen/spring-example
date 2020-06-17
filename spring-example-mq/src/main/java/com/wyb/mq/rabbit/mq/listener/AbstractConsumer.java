package com.wyb.mq.rabbit.mq.listener;

import com.alibaba.fastjson.JSON;
import com.wyb.mq.rabbit.mq.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Marcher丶
 */
public class AbstractConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumer.class);

    public void onMessage(Message msg) {
        String body = null;

        try {
            // 日志链路跟踪逻辑
            body = new String(msg.getBody(), StandardCharsets.UTF_8);
            SendMessage message = JSON.parseObject(body, SendMessage.class);
            Map<String, String> container = message.getMdcContainer();
            if (container != null) {
                // 日志链路跟踪
                MDC.setContextMap(message.getMdcContainer());
            }
        } catch (Exception e) {
            LOGGER.warn("没有找到MQ消息日志链路数据，无法做日志链路追踪");
        }

        try {
            // 处理消息逻辑
//            doMessage(msg);
            LOGGER.info("成功处理MQ消息, 消息体：{}", body);
        } catch (Exception e) {
            LOGGER.error("处理MQ消息异常 {}, 消息体：{}", JSON.toJSONString(msg), body, e);
        }
    }

}
