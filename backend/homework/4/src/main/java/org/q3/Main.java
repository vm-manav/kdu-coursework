package org.q3;

import org.Logger;

import java.util.Arrays;

public class Main {
    public static <T> void swapTwoElements(T[] array,T firstElement,T secondElement){
        if(!Arrays.asList(array).contains(firstElement) && !Arrays.asList(array).contains(secondElement)){
            System.out.println(firstElement+" and "+secondElement+" not exist");
        } else if (!Arrays.asList(array).contains(firstElement)){
            System.out.println(firstElement+" not exist");
        } else if (!Arrays.asList(array).contains(secondElement)) {
            System.out.println(secondElement+" not exist");
        } else {
            int firstIndex=Arrays.asList(array).indexOf(firstElement);
            int secondIndex=Arrays.asList(array).indexOf(secondElement);
            array[firstIndex]=secondElement;
            array[secondIndex]=firstElement;
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        swapTwoElements(intArray, 1, 4);
        Logger.infoMessage(Arrays.toString(intArray));

        String[] stringArray = {"a", "b", "c", "d"};
        swapTwoElements(stringArray, "a", "d");
        Logger.infoMessage(Arrays.toString(stringArray));
    }
}
