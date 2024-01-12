package org.q3;

import org.Logger;

public class Factors implements Runnable{
    private final int inputNumber;

    public Factors(int number) {
        this.inputNumber = number;
    }
    private void calculateFactors() {
        Logger.infoMessage("Factors of " + inputNumber + ": ");
        for (int i = 1; i <= inputNumber; i++) {
            if (inputNumber % i == 0) {
                Logger.infoMessage(i + " ");
            }
        }
    }

    @Override
    public void run() {
        calculateFactors();
    }
}
