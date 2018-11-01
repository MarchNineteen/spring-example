package com.wyb.mq.rabbit.mq;

import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 发送消息的相关数据 扩展
 *
 * @author Kunzite
 */
public class CustomCorrelationData extends CorrelationData {

    /**
     * 消息体
     */
    private volatile Object message;

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 路由key
     */
    private String routingKey;

    /**
     * 重试次数
     */
    private int retryCount = 0;

    public CustomCorrelationData() {
        super();
    }

    public CustomCorrelationData(String id) {
        super(id);
    }

    public CustomCorrelationData(String id, Object data) {
        this(id);
        this.message = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

}
