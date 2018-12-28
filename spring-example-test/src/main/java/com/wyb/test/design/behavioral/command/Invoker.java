package com.wyb.test.design.behavioral.command;

/**
 * @author Kunzite
 */
public class Invoker {

    private Command[] onCommands;
    private Command[] offCommands;
    private Integer num;

    public Invoker(Integer num) {
        this.onCommands = new Command[num];
        this.offCommands =  new Command[num];
    }

    public void setOnCommand(Command command, int slot) {
        onCommands[slot] = command;
    }

    public void setOffCommand(Command command, int slot) {
        offCommands[slot] = command;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    public Command[] getOnCommands() {
        return onCommands;
    }

    public void setOnCommands(Command[] onCommands) {
        this.onCommands = onCommands;
    }

    public Command[] getOffCommands() {
        return offCommands;
    }

    public void setOffCommands(Command[] offCommands) {
        this.offCommands = offCommands;
    }
}
