package org.digitalbanking.digitalbanking_backend.repositories;

import org.digitalbanking.digitalbanking_backend.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
