package com.wyb.mq.rabbit.config;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * @author Marcher丶
 */
public class RabbitMQConnectionListener implements ConnectionListener {

    public void onCreate(Connection connection) {
        System.out.println("服务器已启动...");
    }

    public void onClose(Connection connection) {
        System.out.println("服务器已关闭...");
    }
}
