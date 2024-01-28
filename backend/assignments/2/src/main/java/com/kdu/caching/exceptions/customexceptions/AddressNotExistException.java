package com.kdu.caching.exceptions.customexceptions;

public class AddressNotExistException extends RuntimeException{
    /**
     * Custom exception class representing an exception for non-existent addresses.
     * @param errorMessage A String containing the error message for the exception.
     */
    public AddressNotExistException(String errorMessage) {
        super(errorMessage);
    }

}
