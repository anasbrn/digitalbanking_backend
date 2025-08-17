package org.digitalbanking.digitalbanking_backend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.digitalbanking.digitalbanking_backend.dtos.*;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.digitalbanking.digitalbanking_backend.entities.CurrentAccount;
import org.digitalbanking.digitalbanking_backend.entities.Customer;
import org.digitalbanking.digitalbanking_backend.entities.SavingAccount;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;
import org.digitalbanking.digitalbanking_backend.exceptions.BankAccountNotFound;
import org.digitalbanking.digitalbanking_backend.mappers.AccountOperationHistoryMapper;
import org.digitalbanking.digitalbanking_backend.mappers.BankAccountMapper;
import org.digitalbanking.digitalbanking_backend.mappers.CustomerMapper;
import org.digitalbanking.digitalbanking_backend.repositories.BankAccountRepository;
import org.digitalbanking.digitalbanking_backend.requests.BankAccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final CustomerServiceImpl customerServiceImpl;
    private final CustomerMapper customerMapper;
    private final OperationServiceImpl operationServiceImpl;
    private final AccountOperationHistoryMapper accountOperationHistoryMapper;

    @Override
    public BankAccountDTO findById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(
                () -> new BankAccountNotFound("Account with id: " + id + " not found")
        );
        log.info("Bank Account with id: " + id + " found");
        return bankAccountMapper.toDto(bankAccount);
    }

    @Override
    public AccountOperationHistoryDTO findById(String id, Pageable pageable) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(
                () -> new BankAccountNotFound("Account with id: " + id + " not found")
        );
        Page<OperationDTO> operationDTOS = operationServiceImpl.findByBankAccountId(id, pageable);
        log.info("Getting bank account operations history");

        return accountOperationHistoryMapper.toDto(bankAccount, operationDTOS, pageable.getPageNumber());
    }

    @Override
    public List<BankAccountDTO> findByCustomerId(String customerId, Pageable pageable) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByCustomerId(customerId, pageable);
        return  bankAccounts.stream().map(bankAccountMapper::toDto).collect(Collectors.toList());
    }

    public BankAccountDTO saveBankAccount(BankAccountRequest request) {
        String customerId = request.getCustomerId();
        String type = request.getType();
        double balance = request.getBalance();
        String currency = request.getCurrency();
        Double interestRate = request.getInterestRate();
        Double overDraft = request.getOverDraft();

        CustomerDTO customerDTO = customerServiceImpl.findCustomerById(customerId);
        Customer customer = customerMapper.toEntity(customerDTO);

        if (type.equalsIgnoreCase("sa")) {
            log.info("save saving account");
            return saveSavingAccount(balance, currency, customer, interestRate);
        } else if (type.equalsIgnoreCase("ca")) {
            log.info("save current account");
            return saveCurrentAccount(balance, currency, customer, overDraft);
        } else {
            throw new IllegalArgumentException("Bank account type not supported, " + type);
        }
    }

    private SavingAccountDTO saveSavingAccount(
            double balance,
            String currency,
            Customer customer,
            double interestRate
    ) {
        SavingAccount savingAccount = SavingAccount
                .builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(balance)
                .currency(currency)
                .status(AccountStatus.CREATED)
                .customer(customer)
                .interestRate(interestRate)
                .build();

        SavingAccount savedSavingAccount = bankAccountRepository.save(savingAccount);
        return bankAccountMapper.toSavingAccountDto(savedSavingAccount);
    }

    private CurrentAccountDTO saveCurrentAccount(
            double balance,
            String currency,
            Customer customer,
            double overDraft
    ) {
        CurrentAccount currentAccount = CurrentAccount
                .builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(balance)
                .currency(currency)
                .status(AccountStatus.CREATED)
                .customer(customer)
                .overDraft(overDraft)
                .build();

        CurrentAccount savedCurrentAccount = bankAccountRepository.save(currentAccount);
        return bankAccountMapper.toCurrentAccountDto(savedCurrentAccount);
    }
}
