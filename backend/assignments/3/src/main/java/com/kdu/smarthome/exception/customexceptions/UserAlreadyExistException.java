package com.kdu.smarthome.exception.customexceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
