package org.digitalbanking.digitalbanking_backend.dtos;

import lombok.Data;

@Data
public class SavingAccountDTO extends BankAccountDTO {
    private double interestRate;
}
