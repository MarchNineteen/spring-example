/*
 * @(#)Product    Created on 2020/7/21
 * Copyright (c) 2020 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.mq.rabbit.mq.base;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author Marcher丶
 * @version $$ Revision: 1.0 $$, $$ Date: 2020/7/21 14:48 $$
 */
public class Producer {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
