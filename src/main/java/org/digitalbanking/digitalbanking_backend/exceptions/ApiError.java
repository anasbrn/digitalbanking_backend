package org.digitalbanking.digitalbanking_backend.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private int status;
    String error;
    String message;
    private LocalDateTime timestamp;

    public ApiError(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
