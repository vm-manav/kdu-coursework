package com.kdu.smarthome.exception.customexceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
