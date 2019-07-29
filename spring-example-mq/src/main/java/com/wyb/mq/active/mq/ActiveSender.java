package com.wyb.mq.active.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyb.mq.active.constants.ActiveConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kunzite
 */
@Slf4j
@Component
public class ActiveSender {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JmsTemplate jmsTemplate;

    @Async
    public void sender(MessageDTO messageDTO) {
        try {
            messageDTO.setId(ActiveConstants.MQID_KEY_PREFIX + "requests");
            jmsTemplate.convertAndSend(ActiveConstants.active_test_queue, objectMapper.writeValueAsString(messageDTO));
        } catch (Exception ex) {
            log.error("[sendMessage]send exception {}", ex);
        }
    }
}
