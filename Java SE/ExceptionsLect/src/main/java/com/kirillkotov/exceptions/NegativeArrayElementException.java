package com.kirillkotov.exceptions;

public class NegativeArrayElementException extends RuntimeException{
    public NegativeArrayElementException() {
    }

    public NegativeArrayElementException(String message) {
        super(message);
    }

}
