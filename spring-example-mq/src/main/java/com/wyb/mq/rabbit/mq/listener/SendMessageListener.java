package com.wyb.mq.rabbit.mq.listener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.wyb.mq.rabbit.constants.RabbitConstants;
import com.wyb.mq.rabbit.mq.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 死信队列处理消息
 *
 * @author Kunzite
 */
@Service
public class SendMessageListener {

    private final Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

    @RabbitListener(queues = RabbitConstants.QUEUE_NAME_SEND_COUPON)
    public void process(SendMessage sendMessage, Channel channel, Message message) throws Exception {
        logger.info("[{}]处理优惠券队列消息队列接收数据，消息体：{}", RabbitConstants.QUEUE_NAME_SEND_COUPON, JSON.toJSONString(sendMessage));

        System.out.println(message.getMessageProperties().getDeliveryTag());

//        channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);// true 重新存入队列，发给下一个订阅者 false直接移除
        try {
            // 参数校验
            Assert.notNull(sendMessage, "sendMessage 消息体不能为NULL");
//            System.out.println(1/0);
            // TODO 处理消息

            // 确认消息已经消费成功 multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("MQ消息处理异常，消息体:{}", message.getMessageProperties().getCorrelationId(), JSON.toJSONString(sendMessage), e);

            // 确认消息已经消费消费失败，将消息发给下一个消费者
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitListener(queues = RabbitConstants.QUEUE_NAME_DEAD_QUEUE)
    public void processDlx(SendMessage sendMessage, Channel channel, Message message) throws Exception {
        logger.info("[{}]处理死信队列消息队列接收数据，消息体：{}", RabbitConstants.QUEUE_NAME_DEAD_QUEUE, JSON.toJSONString(sendMessage));

        System.out.println(message.getMessageProperties().getDeliveryTag());

        try {
            // 参数校验
            Assert.notNull(sendMessage, "sendMessage 消息体不能为NULL");

            // TODO 处理消息

            // 确认消息已经消费成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.error("MQ消息处理异常，消息体:{}", message.getMessageProperties().getCorrelationId(), JSON.toJSONString(sendMessage), e);

            // 确认消息已经消费消费失败，将消息发给下一个消费者
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
