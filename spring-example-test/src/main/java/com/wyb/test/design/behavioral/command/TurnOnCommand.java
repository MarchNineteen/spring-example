package com.wyb.test.design.behavioral.command;

/**
 * @author Kunzite
 */
public class TurnOnCommand implements Command {

    private Light light;

    public TurnOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("开灯");
        light.turnOn();
    }
}
