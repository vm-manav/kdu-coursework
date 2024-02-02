package com.example.assesment2.exceptions.customexceptions;

public class NoEntityFoundWithGivenIdException extends RuntimeException{
    public NoEntityFoundWithGivenIdException(String errorMessage) {
        super(errorMessage);
    }
}
