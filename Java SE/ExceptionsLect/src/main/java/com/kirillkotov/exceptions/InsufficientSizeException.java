package com.kirillkotov.exceptions;

public class InsufficientSizeException extends Exception{
    public InsufficientSizeException() {
    }

    public InsufficientSizeException(String message) {
        super(message);
    }
}
