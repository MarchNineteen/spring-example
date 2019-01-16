package com.wyb.test.design.behavioral.memento;

/**
 * @author Kunzite
 * Memento Interface to Originator
 * This interface allows the originator to restore its state
 */
public interface PreviousCalculationToOriginator {
    int getFirstNumber();

    int getSecondNumber();
}
