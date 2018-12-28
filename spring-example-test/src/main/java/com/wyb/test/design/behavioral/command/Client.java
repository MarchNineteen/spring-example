package com.wyb.test.design.behavioral.command;

/**
 * @author Kunzite
 * 命令:将命令封装成对象中，具有以下作用：
 * 使用命令来参数化其它对象
 * 将命令放入队列中进行排队
 * 将命令的操作记录到日志中
 * 支持可撤销的操作
 */
public class Client {

    public static void main(String[] args) {
        Invoker invoker = new Invoker(7);
        Light light = new Light();
        Command onCommand = new TurnOnCommand(light);
        Command offCommand = new TurnOffCommand(light);

        invoker.setOnCommand(onCommand, 0);
        invoker.setOffCommand(offCommand, 0);
        invoker.onButtonWasPushed(0);
        invoker.offButtonWasPushed(0);

    }
}
