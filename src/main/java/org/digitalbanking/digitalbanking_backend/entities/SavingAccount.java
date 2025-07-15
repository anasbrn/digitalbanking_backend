package org.digitalbanking.digitalbanking_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue("SA")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SavingAccount extends BankAccount {
    private double interestRate;
}

