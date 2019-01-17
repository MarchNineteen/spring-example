---
title: Java设计模式：桥接器
date: 2019-01-08 
keywords: bridge
categories: [java-design-patterns]
---
# 定义(目的)
将抽象与实现分离开来，使它们可以独立变化。

抽象与实现解耦，例如一个生产线的机器，将参数设定与具体生产的过程实现分离开，使整个生产线更加灵活，用一套参数也可以使用不同的工作模式。

其中参数设定与生产过程属于两种不同的维度，桥接模式所做的事情就是将不同的维度联结在一起！

# 实现原理

- 在一个类中通过组合另一个对象，调用时只需在当前类调用对象方法即可，实现解耦。

### 代码实现：
    
   定义电视机抽象类,拥有3个方法。
    
    public abstract class Tv {
    
        public abstract void on();
    
        public abstract void off();
    
        public abstract void tuneTunnel();
   
   定义一个电视机的实现类，Sony电视机。
   
    public class SonyTv extends Tv {
    
        @Override
        public void on() {
            System.out.println("SonyTv,on");
        }
    
        @Override
        public void off() {
            System.out.println("SonyTv,off");
        }
    
        @Override
        public void tuneTunnel() {
            System.out.println("SonyTv,tuneTunnel");
        }
   
   定义一个遥控器抽象类。组合了一个电视机对象。
   
    protected Tv tv;
    
        public RemoteControl(Tv tv) {
            this.tv = tv;
        }
    
        public abstract void on();
    
        public abstract void off();
    
        public abstract void tuneTunnel();   
    
   定义一个遥控器的实现类，Sony电视机遥控器。
    
    public class SonyRemoteControl extends RemoteControl {
    
        public SonyRemoteControl(Tv tv) {
            super(tv);
        }
    
        @Override
        public void on() {
            System.out.println("sonyRemoteControl,on");
            tv.on();
        }
    
        @Override
        public void off() {
            System.out.println("sonyRemoteControl,off");
            tv.off();
        }
    
        @Override
        public void tuneTunnel() {
            System.out.println("sonyRemoteControl,tuneTunnel");
            tv.off();
        }
    } 
   
   在生成sony电视机遥控器时，传入一个sony电视机对象，即可使用该遥控器实现该电视机的状态变换。
   
    public class Client {
   
       public static void main(String[] args) {
           Tv sonyTv = new SonyTv();
           SonyRemoteControl sonyRemoteControl = new SonyRemoteControl(sonyTv);
           sonyRemoteControl.on();
           sonyRemoteControl.off();
           sonyRemoteControl.tuneTunnel();
       }
    }
   
    
   