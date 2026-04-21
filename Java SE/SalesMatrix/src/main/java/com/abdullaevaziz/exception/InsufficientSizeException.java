package com.abdullaevaziz.exception;

public class InsufficientSizeException extends Exception{

    public InsufficientSizeException() {
    }

    public InsufficientSizeException(String message) {
        super(message);
    }
}
