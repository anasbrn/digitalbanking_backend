package org.digitalbanking.digitalbanking_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;

import java.sql.Date;

@DiscriminatorValue("CA")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CurrentAccount extends BankAccount {
    private double overDraft;
}

