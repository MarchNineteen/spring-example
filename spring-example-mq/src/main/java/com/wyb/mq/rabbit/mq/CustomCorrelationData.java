package com.wyb.mq.rabbit.mq;

import lombok.Data;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.support.CorrelationData;

import java.util.Map;

/**
 * 发送消息的相关数据 扩展
 *
 * @author Kunzite
 */
@Data
public class CustomCorrelationData extends CorrelationData {

    /**
     * MDC容器
     * 获取父线程MDC中的内容，做日志链路
     */
    private Map<String, String> mdcContainer = MDC.getCopyOfContextMap();

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
}
