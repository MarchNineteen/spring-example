---
title: Java设计模式：外观模式
date: 2019-01-24 
keywords: flyweight
categories: [java-design-patterns]
---
# 定义(目的)
提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。

# 实现原理

定义一个外观对象，在内部进行功能的封装，功能具体是在都在外观类的内部，其它方法要使用该功能
调用外观对象的方法即可。

### 代码实现：
    
   定义一个电影播放系统，方法代表需要功能所需步骤
    
    public class SubSystem {
    
        public void turnOnTV() {
            System.out.println("turnOnTV()");
        }
    
        public void setCD(String cd) {
            System.out.println("setCD( " + cd + " )");
        }
    
        public void starWatching() {
            System.out.println("starWatching()");
        }
    
    }
   
   定义一个外观类，封装了看电影的三步骤，客户端只需调用外观类的方法即可实现功能
   
    public class Facade {
    
        private SubSystem subSystem = new SubSystem();
    
        public void watchMovie(String name) {
            subSystem.turnOnTV();
            subSystem.setCD(name);
            subSystem.starWatching();
        }
    }
   
   测试：
   
    public class Client {
    
        public static void main(String[] args) {
            Facade facade = new Facade();
            facade.watchMovie("家有喜事");
        }
    }
    
   测试：
   
    turnOnTV()
    setCD( 家有喜事 )
    starWatching()
   