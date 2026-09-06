package com.bhalchandra.eventProcessor.exception;

public class insuffFunds extends RuntimeException {

    public insuffFunds(String message) {
        super(message);
    }
}