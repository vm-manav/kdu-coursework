package org.q3;

public class Main {
    public static void main(String[] args) {

        int number=10;
        Factorial factorial = new Factorial(number);
        Factors factors = new Factors(number);

        Thread factorialThread=new Thread(factorial);
        Thread factorThread=new Thread(factors);
        factorThread.start();
        factorialThread.start();

        try {
            factorialThread.join();
            factorThread.join();
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }
}



