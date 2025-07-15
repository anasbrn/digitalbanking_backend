package org.digitalbanking.digitalbanking_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue("CA")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder()
public class CurrentAccount extends BankAccount {
    private double overDraft;
}

