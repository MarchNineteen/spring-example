package com.wyb.test.design.behavioral.visitor;

/**
 * @author Kunzite
 */
public interface Element {

    void accept(Visitor visitor);

}
