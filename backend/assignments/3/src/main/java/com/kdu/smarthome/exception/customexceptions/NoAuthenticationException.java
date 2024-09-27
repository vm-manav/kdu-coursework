package com.kdu.smarthome.exception.customexceptions;

public class NoAuthenticationException extends RuntimeException{
    public NoAuthenticationException(String error) {
        super(error);
    }
}
