package com.wyb.mq.active.mq;

import java.io.Serializable;

/**
 * 消息对象
 *
 * @author kunzite
 */
public class MessageDTO<T> implements Serializable {
    private static final long serialVersionUID = -8036862921127192852L;

    private String id;// 消息发送ID

    private String topic;// 消息类型

    private Boolean isSucceed;// 是否成功

    private T data;// 消息发送对象

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getSucceed() {
        return isSucceed;
    }

    public void setSucceed(Boolean succeed) {
        isSucceed = succeed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
