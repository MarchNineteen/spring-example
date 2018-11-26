package com.wyb.mq.active.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyb.mq.active.constants.ActiveConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kunzite
 */
@Component
@Slf4j
public class ActiveListener {


    @Resource
    private ObjectMapper objectMapper;

    // 异步操作
    Function<MessageDTO, MessageDTO> testMsg = messageDTO -> {
        try {
            String s = objectMapper.readValue((String) messageDTO.getData(), String.class);
            System.out.printf(s);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("[RequsetQueue][test]error, msgId {}", messageDTO.getId());
        }
        return messageDTO;
    };

    // 异步操作注册中心
    Supplier<Map<String, Function<MessageDTO, MessageDTO>>> mapSupplier = () -> {
        Map<String, Function<MessageDTO, MessageDTO>> maps = new HashMap<>();
        maps.put("testMsg", testMsg);
        return maps;
    };

    @JmsListener(destination = ActiveConstants.active_test_queue)
    public void onMessage(String message) {
        try {
            MessageDTO messageDTO = objectMapper.readValue(message, MessageDTO.class);
            Optional.of(mapSupplier.get().get(messageDTO.getTopic())).ifPresent(x -> {
                MessageDTO m = x.apply(messageDTO);
            });
        } catch (Exception ex) {
            log.error("[RequsetQueue][onMessage]error ", ex);
        }
    }
}
