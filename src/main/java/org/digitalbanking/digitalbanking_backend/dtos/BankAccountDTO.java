package org.digitalbanking.digitalbanking_backend.dtos;

import lombok.Data;
import org.digitalbanking.digitalbanking_backend.entities.Customer;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;

import java.util.Date;

@Data
public abstract class BankAccountDTO {
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    private AccountStatus status;
    private CustomerDTO customer;
}
