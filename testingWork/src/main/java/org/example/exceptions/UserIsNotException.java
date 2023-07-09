package org.example.exceptions;

public class UserIsNotException extends RuntimeException {
    public UserIsNotException(String message) {
        super(message);
    }
}
