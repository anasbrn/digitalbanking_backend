package org.digitalbanking.digitalbanking_backend.exceptions;

public class SoldInsufficientException extends RuntimeException {
    public SoldInsufficientException(String message) {
        super(message);
    }
}
