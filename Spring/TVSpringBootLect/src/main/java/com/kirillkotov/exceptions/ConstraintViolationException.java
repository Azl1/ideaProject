package com.kirillkotov.exceptions;

public class ConstraintViolationException extends Exception{
    public ConstraintViolationException() {
    }

    public ConstraintViolationException(String message) {
        super(message);
    }
}
