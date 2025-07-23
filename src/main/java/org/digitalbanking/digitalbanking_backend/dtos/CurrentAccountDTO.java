package org.digitalbanking.digitalbanking_backend.dtos;

import lombok.Data;

@Data
public class CurrentAccountDTO extends BankAccountDTO {
    private double overDraft;
}
