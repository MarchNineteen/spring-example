/*
 * @(#)Consumer    Created on 2020/7/21
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.mq.rabbit.mq.base;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/7/21 14:49 $$
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
