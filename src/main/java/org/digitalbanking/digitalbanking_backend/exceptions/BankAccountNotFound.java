package org.digitalbanking.digitalbanking_backend.exceptions;

public class BankAccountNotFound extends RuntimeException {
    public BankAccountNotFound(String message) {
        super(message);
    }
}
