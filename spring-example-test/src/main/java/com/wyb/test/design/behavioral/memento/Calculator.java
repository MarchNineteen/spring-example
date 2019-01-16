package com.wyb.test.design.behavioral.memento;

/**
 * @author Kunzite
 * Originator Interface
 */
public interface Calculator {
    // Create Memento
    PreviousCalculationToCareTaker backupLastCalculation();

    // setMemento
    void restorePreviousCalculation(PreviousCalculationToCareTaker memento);

    int getCalculationResult();

    void setFirstNumber(int firstNumber);

    void setSecondNumber(int secondNumber);

}
