package com.wyb.mq.rabbit.mq;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.Map;

/**
 * 发放消息的消息体
 *
 * @author Kunzite
 */
@Data
public class SendMessage implements Serializable {
    private static final long serialVersionUID = -4731326195678504565L;

    /**
     * MDC容器
     * 获取父线程MDC中的内容，做日志链路
     */
    private Map<String, String> mdcContainer = MDC.getCopyOfContextMap();

    /**
     * 消息ID(消息的唯一标示)
     */
    private String messageId;

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

}
