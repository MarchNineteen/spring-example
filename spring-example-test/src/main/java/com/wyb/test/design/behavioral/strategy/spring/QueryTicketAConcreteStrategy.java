package com.wyb.test.design.behavioral.strategy.spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcher丶
 */
@Service("1")
public class QueryTicketAConcreteStrategy implements QueryTicketStrategy {

    @Override
    public List<Ticket> getTicketList() {
        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket("去程机票"));
        list.add(new Ticket("返程机票"));
        return list;
    }

}

