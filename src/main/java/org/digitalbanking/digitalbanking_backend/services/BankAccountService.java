package org.digitalbanking.digitalbanking_backend.services;

import org.digitalbanking.digitalbanking_backend.dtos.AccountOperationHistoryDTO;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface BankAccountService {
    BankAccountDTO findById(String id);

    AccountOperationHistoryDTO findById(String id, Pageable pageable);

    List<BankAccountDTO> findByCustomerId(String customerId, Pageable pageable);
}
