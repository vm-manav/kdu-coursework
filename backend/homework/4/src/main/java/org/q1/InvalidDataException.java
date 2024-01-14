package org.q1;

public class InvalidDataException extends Exception{
    public InvalidDataException(String message,Throwable e){
        super(message,e);
    }
}
