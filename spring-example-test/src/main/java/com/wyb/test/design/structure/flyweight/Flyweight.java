package com.wyb.test.design.structure.flyweight;

/**
 * @author Kunzite
 */
public interface Flyweight {

    /**
     * 外部状态，每个享元对象的外部状态不同
     *
     * @param extrinsicState
     */
    public void extrinsicState(String extrinsicState);
}
