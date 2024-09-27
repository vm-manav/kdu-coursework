package com.kdu.smarthome.exception.customexceptions;

public class DeviceNotFoundWithUserNameException extends RuntimeException{
    public  DeviceNotFoundWithUserNameException(String error) {
        super(error);
    }
}
