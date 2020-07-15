package com.wyb.test.design.behavioral.strategy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcher丶
 */
@Service
public class QueryTicketStrategyContext {

    @Autowired
    private Map<String, QueryTicketStrategy> map = new HashMap<>();


    public void getTicketList(int type) {
        String typeStr = String.valueOf(type);
        System.out.println(map.get(typeStr).getTicketList());
    }

}
