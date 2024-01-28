package com.kdu.caching.exceptions.customexceptions;

public class InvalidLocationPointException extends RuntimeException{
    /**
     * Custom exception class representing an exception for invalid location points.
     *
     * @param errorMessage A String containing the error message for the exception.
     */
    public InvalidLocationPointException(String errorMessage) {
        super(errorMessage);
    }
}
