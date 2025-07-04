package org.digitalbanking.digitalbanking_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@DiscriminatorColumn(name = "type", length = 4)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity @Data @NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<Operation> operations;
}

