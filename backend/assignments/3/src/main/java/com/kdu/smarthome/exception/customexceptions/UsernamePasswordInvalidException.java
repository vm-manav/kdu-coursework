package com.kdu.smarthome.exception.customexceptions;

public class UsernamePasswordInvalidException extends RuntimeException{
    public UsernamePasswordInvalidException(String error) {
        super(error);
    }
}
