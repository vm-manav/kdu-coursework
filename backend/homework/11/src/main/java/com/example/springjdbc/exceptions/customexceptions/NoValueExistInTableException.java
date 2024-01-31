package com.example.springjdbc.exceptions.customexceptions;

public class NoValueExistInTableException extends RuntimeException{
    public NoValueExistInTableException(String error) {
        super(error);
    }
}
