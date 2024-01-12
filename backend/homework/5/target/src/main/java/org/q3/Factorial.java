package org.q3;

import org.Logger;

public class Factorial implements Runnable{
    private final int inputNumber;

    public Factorial(int number) {
        this.inputNumber = number;
    }
    private void calculateFactorial() {
        long factorial=1;
        for(int i = 1; i <= inputNumber; i++) {
            factorial *= i;
        }
        Logger.infoMessage("Factorial of "+inputNumber+": "+factorial);
    }
    @Override
    public void run() {
        calculateFactorial();
    }
}
