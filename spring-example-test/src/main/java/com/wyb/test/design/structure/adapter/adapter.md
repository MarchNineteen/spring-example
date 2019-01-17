---
title: Java设计模式：适配器
date: 2019-01-08 
keywords: adapter
categories: [java-design-patterns]
---
# 定义(目的)
适配器就是一种适配中间件，它存在于不匹配的二者之间，用于连接二者，将不匹配变得匹配，简单点理解就是平常所见的转接头，转换器之类的存在。

把一个类接口转换成另一个用户需要的接口。

# 实现原理

- 通过组合的方式。
- 通过继承来实现适配器功能。

### 代码实现：
    
   定义一个鸭接口 方法为鸭子叫
    
    public interface Duck {
    
        void quack();
   
   定义一个火鸡接口 方法为鸡叫
   
    public interface Turkey {
   
       void gobble();    
   
   鸭子实现类
   
    public class WildDuck implements Duck {
    
        @Override
        public void quack() {
            System.out.println("鸭子叫");
        }
    }    
    
   火鸡实现类
    
    public class WildTurkey implements Turkey {
    
        @Override
        public void gobble() {
            System.out.println("火鸡叫");
        }
    } 
    
   现在鸭子只能发出鸭子叫，鸡只能发出鸡叫，我们想要鸭子发出鸡的叫声。添加鸭子适配器。
   
    public class DuckAdapter implements Turkey {
   
       Duck duck;
   
       public DuckAdapter(Duck duck) {
           this.duck = duck;
       }
   
       @Override
       public void gobble() {
           duck.quack();
       }
    }
   
   组合方式，适配器构造器需要一个鸭子对象，适配器实现了火鸡的接口，通过调用鸭子对象的方法，实现了鸭子发生了鸡叫。
   
    public class DuckAdapter extends Turkey WildTurkey implements Turkey {
      
          @Override
          public void gobble() {
              gobble.quack();
          }
       }
       
   继承方式，适配器构造器需要一个鸭子对象，适配器实现了火鸡的接口，通过调用鸭子对象的方法，实现了鸭子发生了鸡叫。
   