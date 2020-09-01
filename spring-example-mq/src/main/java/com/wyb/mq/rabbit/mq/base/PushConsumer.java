package com.wyb.mq.rabbit.mq.base;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author Marcher丶
 * push模式 服务端主动推送至客户端
 * 优点:及时性高
 * 缺点：1.客户端处理能力不足时 容易造成消息堆积，处理缓慢 2.服务端需要维护每次传输状态，以防消息传递失败进行重试。
 * 使用场景：对于数据实时性要求高的场景，就比较适用与push模式。
 */
public class PushConsumer {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        while (true) {
            // 消费消息
            boolean autoAck = false;
            String consumerTag = "";
            // DefaultConsumer consumer = new DefaultConsumer(channel);
            // consumer.handleDelivery(QUEUE_NAME, autoAck, consumerTag, ;

            channel.basicConsume(QUEUE_NAME, autoAck, consumerTag, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                        byte[] body) throws IOException {
                    System.err.println("-----------consume message----------");
                    System.err.println("consumerTag: " + consumerTag);
                    System.err.println("envelope: " + envelope);
                    System.err.println("properties: " + properties);
                    System.err.println("body: " + new String(body));

                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    long deliveryTag = envelope.getDeliveryTag();
                    // (process the message components here . .. )
                    channel.basicAck(deliveryTag, false);
                }
            });
        }
    }
}
