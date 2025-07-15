package org.digitalbanking.digitalbanking_backend.repositories;

import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
