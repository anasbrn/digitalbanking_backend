package org.digitalbanking.digitalbanking_backend.repositories;

import org.digitalbanking.digitalbanking_backend.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findByBankAccountId(String bankAccountId, Pageable pageable);
}
