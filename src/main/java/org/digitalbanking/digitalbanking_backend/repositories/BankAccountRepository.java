package org.digitalbanking.digitalbanking_backend.repositories;

import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByCustomerId(String customerId, Pageable pageable);
}
