package com.wyb.test.design.behavioral.visitor;

/**
 * @author Kunzite
 */
public interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}
