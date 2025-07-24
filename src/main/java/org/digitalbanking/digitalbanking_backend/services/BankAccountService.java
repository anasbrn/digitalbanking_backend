package org.digitalbanking.digitalbanking_backend.services;

import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;

interface BankAccountService {
    BankAccountDTO findById(String id);
}
