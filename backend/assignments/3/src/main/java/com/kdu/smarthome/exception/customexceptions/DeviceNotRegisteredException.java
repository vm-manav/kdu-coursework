package com.kdu.smarthome.exception.customexceptions;

public class DeviceNotRegisteredException extends RuntimeException{
    public DeviceNotRegisteredException(String error) {
        super(error);
    }
}
