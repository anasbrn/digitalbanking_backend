package org.digitalbanking.digitalbanking_backend.services;

import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface OperationService {
    Page<OperationDTO> findByBankAccountId(String bankAccountId, Pageable pageable);
}
