package com.example.springjpa.exceptions.customexceptions;

public class NoShiftUserFoundWithGivenIdException extends RuntimeException{
    public NoShiftUserFoundWithGivenIdException(String errorMessage) {
        super(errorMessage);
    }
}
