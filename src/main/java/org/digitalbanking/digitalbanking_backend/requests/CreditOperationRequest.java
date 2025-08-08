package org.digitalbanking.digitalbanking_backend.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreditOperationRequest {
    @NotNull(message = "Bank Account Id is required")
    String bankAccountId;
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    Double amount;
}
