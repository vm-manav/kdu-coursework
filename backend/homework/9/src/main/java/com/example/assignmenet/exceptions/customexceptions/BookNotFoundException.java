package com.example.assignmenet.exceptions.customexceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String stringMessage) {
        super(stringMessage);
    }
}

