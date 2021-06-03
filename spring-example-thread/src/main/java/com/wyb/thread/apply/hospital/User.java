package com.wyb.thread.apply.hospital;

/**
 * Description:
 *
 * @author: Marcher丶
 * @Date: 2021-05-22 21:10
 **/
public class User {

    private RegisteredMachine machine;

    public User(RegisteredMachine machine) {
        this.machine = machine;
    }

    public Integer getNum() {
        return this.machine.addNum();
    }
}
