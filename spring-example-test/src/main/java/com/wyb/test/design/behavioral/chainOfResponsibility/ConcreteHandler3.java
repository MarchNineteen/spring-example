package com.wyb.test.design.behavioral.chainOfResponsibility;

/**
 * @author Kunzite
 */
public class ConcreteHandler3 extends Handler {

    public ConcreteHandler3(Handler successor) {
        super(successor);
    }

    @Override
    protected void handleRequest(Request request) {
        if (RequestType.TYPE3 == request.getType()) {
            System.out.println(request.getName() + " is handle by ConcreteHandler3");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
