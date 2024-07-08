package org.autech.Exception;

public class OMSException extends RuntimeException{
    public OMSException(String ex){
        super(String.format("OMS Exception Occurred : %s", ex));
    }
}
