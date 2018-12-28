package com.wyb.test.design.behavioral.command;

/**
 * @author Kunzite
 */
public class TurnOffCommand implements Command {

    private Light light;

    public TurnOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("关灯");
        light.turnOff();
    }
}
