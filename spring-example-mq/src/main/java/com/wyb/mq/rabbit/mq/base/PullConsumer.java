package com.wyb.mq.rabbit.mq.base;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;

/**
 * @author Marcher丶
 * pull模式 客户端主动去服务端拉去消息
 * 优点：1.根据自身能力去获取数据 2.传输失败不需要重试，数据还在服务器
 * 缺点：1.主动拉去间隔要设置好
 * 使用场景：
 * 对于服务端生产消息数据比较大时，而消费端处理比较复杂，消费能力相对较低时，这种情况就适用pull模式。
 */
public class PullConsumer {
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        GetResponse response = channel.basicGet(QUEUE_NAME, false);
        System.out.println(new String(response.getBody()));
        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
    }
}
