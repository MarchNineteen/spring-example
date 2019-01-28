---
title: Java设计模式：责任链模式
date: 2019-01-24 
keywords: chainOfResponsibility
categories: [java-design-patterns]
---
# 定义(目的)
使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。

# 实现原理

定义个抽象类，依赖一个本身对象，每个继承类，即可传入其它的继承类实现对象链。

### 代码实现：
    
   定义处理器抽象类 依赖一个本身对象，通过构造器传入，拥有一个处理请求的抽象方法 
    
    protected Handler successor;
    
        public Handler(Handler successor) {
            this.successor = successor;
        }
    
    
        protected abstract void handleRequest(Request request);
   
   请求参数类
   
    public class Request {
    
        private RequestType type;
        private String name;
    
    
        public Request(RequestType type, String name) {
            this.type = type;
            this.name = name;
        }
    
    
        public RequestType getType() {
            return type;
        }
    
    
        public String getName() {
            return name;
        }
    
    }
    
   请求类型枚举类 不同的请求类型 调用不同的处理器
    
    public enum RequestType {
        TYPE1, TYPE2, TYPE3
    } 
    
   定义处理器类1继承抽象类处理类型为TYPE1的请求
      
    public class ConcreteHandler1 extends Handler {
   
       public ConcreteHandler1(Handler successor) {
           super(successor);
       }
   
       @Override
       protected void handleRequest(Request request) {
           if (RequestType.TYPE1 == request.getType()) {
               System.out.println(request.getName() + " is handle by ConcreteHandler1");
               return;
           }
           if (successor != null) {
               successor.handleRequest(request);
           }
       }
    }
   
   定义处理器类2继承抽象类处理类型为TYPE2的请求
         
    public class ConcreteHandler2 extends Handler {
       public ConcreteHandler2(Handler successor) {
           super(successor);
       }
   
       @Override
       protected void handleRequest(Request request) {
           if (request.getType() == RequestType.TYPE2) {
               System.out.println(request.getName() + " is handle by ConcreteHandler2");
               return;
           }
           if (successor != null) {
               successor.handleRequest(request);
           }
       }
    }
    
   测试：
   
    public class Client {
   
       public static void main(String[] args) {
   
           Handler handler1 = new ConcreteHandler1(null);
           Handler handler2 = new ConcreteHandler2(handler1);
   
           Request request1 = new Request(RequestType.TYPE1, "request1");
           handler2.handleRequest(request1);
           Request request2 = new Request(RequestType.TYPE2, "request2");
           handler2.handleRequest(request2);
   
           Handler handler3 = new ConcreteHandler3(handler2);
           Request request3 = new Request(RequestType.TYPE3, "request3");
           handler3.handleRequest(request1);
       }
    }
    
   输出：
   
    request1 is handle by ConcreteHandler1
    request2 is handle by ConcreteHandler2
    
   再定义一个定义处理器类3继承抽象类处理类型为TYPE3的请求
   
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
    
   测试：使用handler3处理request1请求
   
    public class Client {
    
        public static void main(String[] args) {
    
            Handler handler1 = new ConcreteHandler1(null);
            Handler handler2 = new ConcreteHandler2(handler1);
    
            Request request1 = new Request(RequestType.TYPE1, "request1");
            Handler handler3 = new ConcreteHandler3(handler2);
            Request request3 = new Request(RequestType.TYPE3, "request3");
            handler3.handleRequest(request1);
        }
    }
   
   输出：最外层的Handler即调用所有类型的处理请求
   
    request1 is handle by ConcreteHandler1