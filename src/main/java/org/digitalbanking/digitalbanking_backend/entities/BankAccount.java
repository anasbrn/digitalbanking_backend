package org.digitalbanking.digitalbanking_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@DiscriminatorColumn(name = "type", length = 4, discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.REMOVE, orphanRemoval = true) // remove in JPA level
    @OnDelete(action = OnDeleteAction.CASCADE) // remove in db level (this alone enough)
    private List<Operation> operations;

    public abstract boolean canWithdraw(double amount);

    public boolean withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void credit(double amount) {
        balance += amount;
    }
}

