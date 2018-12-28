package com.wyb.test.design.structure.facade;

/**
 * @author Kunzite
 */
public class Facade {

    private SubSystem subSystem = new SubSystem();

    public void watchMovie(String name) {
        subSystem.turnOnTV();
        subSystem.setCD(name);
        subSystem.starWatching();
    }
}
