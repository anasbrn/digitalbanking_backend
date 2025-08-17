package org.digitalbanking.digitalbanking_backend.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.digitalbanking.digitalbanking_backend.entities.Operation;
import org.digitalbanking.digitalbanking_backend.enums.OperationType;
import org.digitalbanking.digitalbanking_backend.exceptions.BankAccountNotFound;
import org.digitalbanking.digitalbanking_backend.exceptions.SoldInsufficientException;
import org.digitalbanking.digitalbanking_backend.mappers.OperationMapper;
import org.digitalbanking.digitalbanking_backend.repositories.BankAccountRepository;
import org.digitalbanking.digitalbanking_backend.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public Page<OperationDTO> findByBankAccountId(String bankAccountId, Pageable pageable) {
        Page<Operation> operations = operationRepository.findByBankAccountId(bankAccountId, pageable);
        return operations.map(operationMapper::toDto);
    }

    @Override
    public void deposit(String bankAccountId, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(
                () -> new BankAccountNotFound("Account with id: " + bankAccountId + " not found")
        );
        bankAccount.credit(amount);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        if (savedBankAccount instanceof BankAccount) {
            Operation operation = Operation
                    .builder()
                    .date(new Date())
                    .amount(amount)
                    .type(OperationType.CREDIT)
                    .bankAccount(savedBankAccount)
                    .build();
            log.info("Deposit credit operation with id: " + operation.getId());
            operationRepository.save(operation);
        }
    }

    @Override
    public void withdraw(String bankAccountId, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(
                () -> new BankAccountNotFound("Account with id: " + bankAccountId + " not found")
        );
        if (amount <= 0) {
            throw new SoldInsufficientException("Amount must be greater than zero");
        }

        if (!bankAccount.withdraw(amount)) {
            throw new SoldInsufficientException("Sold insufficient");
        }

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        Operation operation = Operation
                .builder()
                .date(new Date())
                .amount(amount)
                .type(OperationType.WITHDRAW)
                .bankAccount(savedBankAccount)
                .build();
        log.info("Withdraw operation with id: " + operation.getId());
        operationRepository.save(operation);
    }

    @Override
    public void transfer(String bankAccountId, String recipientBankAccountId, double amount) {
        BankAccount senderBankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(
                () -> new BankAccountNotFound("Sender Bank Account with id: " + bankAccountId + " not found")
        );

        if (amount <= 0) {
            throw new SoldInsufficientException("Amount must be greater than zero");
        }
        boolean senderWithdrawSuccess = senderBankAccount.withdraw(amount);
        if (senderWithdrawSuccess) {
            BankAccount recipientBankAccount = bankAccountRepository.findById(recipientBankAccountId).orElseThrow(
                    () -> new BankAccountNotFound("Recipient Bank Account with id: " + bankAccountId + " not found")
            );
            recipientBankAccount.credit(amount);
            bankAccountRepository.save(senderBankAccount);
            bankAccountRepository.save(recipientBankAccount);
            Operation senderOperation = Operation
                    .builder()
                    .date(new Date())
                    .amount(amount)
                    .description("Transfer " + amount + " to bank account id: " + recipientBankAccountId)
                    .type(OperationType.TRANSFER)
                    .bankAccount(senderBankAccount)
                    .build();

            Operation recipientOperation = Operation
                    .builder()
                    .date(new Date())
                    .amount(amount)
                    .description("Received " + amount + " form bank account id: " + bankAccountId)
                    .type(OperationType.TRANSFER)
                    .bankAccount(recipientBankAccount)
                    .build();
            operationRepository.save(senderOperation);
            operationRepository.save(recipientOperation);
            log.info("Transfer to recipient sender " + recipientBankAccountId + " with operation with id: " + senderOperation.getId());
            log.info("Transfer from sender " + bankAccountId + " with operation with id: " + recipientOperation.getId());
        } else {
            throw new SoldInsufficientException("Sold insufficient");
        }
    }

}
