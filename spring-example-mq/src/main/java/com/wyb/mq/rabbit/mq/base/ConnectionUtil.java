package com.wyb.mq.rabbit.mq.base;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Marcher丶
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        // 设置服务地址
        factory.setHost("111.231.85.51");
        // 端口
        factory.setPort(5672);
        // 设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("test");

        factory.setUsername("test");
        factory.setPassword("test");
        // 通过工程获取连接
        return factory.newConnection();
    }
}
