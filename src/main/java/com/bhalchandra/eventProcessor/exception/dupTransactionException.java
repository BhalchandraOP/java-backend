package com.bhalchandra.eventProcessor.exception;

public class dupTransactionException extends RuntimeException {

    public dupTransactionException(String message) {
        super(message);
    }
}