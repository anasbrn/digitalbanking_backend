package org.digitalbanking.digitalbanking_backend.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.digitalbanking.digitalbanking_backend.annotations.ValidAccountType;

@ValidAccountType
@Data
public class BankAccountRequest {
    @NotNull(message = "balance is required")
    @Positive(message = "balance must be greater than 0")
    Double balance;
    @NotNull(message = "currency is required")
    String currency;
    @NotNull(message = "type is required")
    String type;
    @NotNull(message = "customer is required")
    String customerId;
    Double interestRate;
    Double overDraft;
}
