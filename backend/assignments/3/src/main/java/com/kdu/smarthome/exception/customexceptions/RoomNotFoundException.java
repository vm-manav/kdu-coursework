package com.kdu.smarthome.exception.customexceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String error) {
        super(error);
    }
}
