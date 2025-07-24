package org.digitalbanking.digitalbanking_backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // we can use customerDTO in bankAcountDTO instead of this
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, orphanRemoval = true) // remove in JPA level
    @OnDelete(action = OnDeleteAction.CASCADE) // remove in db level (this alone enough)
    private List<BankAccount> bankAccounts;
}
