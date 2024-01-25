package com.example.assignmenet.exceptions.customexceptions;


public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
