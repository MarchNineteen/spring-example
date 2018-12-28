package com.wyb.test.design.structure.flyweight;

/**
 * @author Kunzite
 */
public interface Flyweight {

    /**
     * 内部状态，享元对象共享内部状态
     *
     * @param extrinsicState
     */
    public void extrinsicState(String extrinsicState);
}
