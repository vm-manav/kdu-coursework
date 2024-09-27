package com.kdu.smarthome.exception.customexceptions;

public class HouseNotFoundException extends RuntimeException{
    public HouseNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
