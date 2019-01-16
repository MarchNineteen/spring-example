/*
 * @(#)GeneralReport    Created on 2019/1/16
 * Copyright (c) 2019 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.design.behavioral.visitor;

/**
 * @author Kunzite
 * @version $$ Revision: 1.0 $$, $$ Date: 2019/1/16 19:05 $$
 */
public class GeneralReport implements Visitor {
    private int customersNo;
    private int ordersNo;
    private int itemsNo;

    @Override
    public void visit(Customer customer) {
        System.out.println(customer.getName());
        customersNo++;
    }

    @Override
    public void visit(Order order) {
        System.out.println(order.getName());
        ordersNo++;
    }

    @Override
    public void visit(Item item) {
        System.out.println(item.getName());
        itemsNo++;
    }

    public void displayResults() {
        System.out.println("Number of customers: " + customersNo);
        System.out.println("Number of orders:    " + ordersNo);
        System.out.println("Number of items:     " + itemsNo);
    }
}
