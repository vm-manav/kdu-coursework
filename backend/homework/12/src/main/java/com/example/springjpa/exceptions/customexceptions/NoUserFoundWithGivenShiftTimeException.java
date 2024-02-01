package com.example.springjpa.exceptions.customexceptions;

public class NoUserFoundWithGivenShiftTimeException extends RuntimeException{
    public NoUserFoundWithGivenShiftTimeException(String error) {
        super(error);
    }
}
