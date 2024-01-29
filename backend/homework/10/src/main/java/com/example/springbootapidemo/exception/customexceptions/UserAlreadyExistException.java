package com.example.springbootapidemo.exception.customexceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
