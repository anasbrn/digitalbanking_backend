package org.digitalbanking.digitalbanking_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.digitalbanking.digitalbanking_backend.exceptions.BankAccountNotFound;
import org.digitalbanking.digitalbanking_backend.mappers.BankAccountMapper;
import org.digitalbanking.digitalbanking_backend.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountDTO findById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(
                () -> new BankAccountNotFound("Account with id: " + id + " not found")
        );
        log.info("Bank Account with id: " + id + " found");
        return bankAccountMapper.toDto(bankAccount);
    }
}
