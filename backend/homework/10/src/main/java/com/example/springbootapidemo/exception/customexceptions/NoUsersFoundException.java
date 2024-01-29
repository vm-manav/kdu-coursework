package com.example.springbootapidemo.exception.customexceptions;

public class NoUsersFoundException extends RuntimeException{
    public NoUsersFoundException(String errorMessage) {
        super(errorMessage);
    }
}
