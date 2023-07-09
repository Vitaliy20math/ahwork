package org.example.exceptions;

public class UncorrectJsonTransfer extends RuntimeException{
    public UncorrectJsonTransfer(String message) {
        super(message);
    }
}
