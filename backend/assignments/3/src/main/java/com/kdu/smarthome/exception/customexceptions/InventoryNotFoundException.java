package com.kdu.smarthome.exception.customexceptions;

public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(String error){
        super(error);
    }
}
