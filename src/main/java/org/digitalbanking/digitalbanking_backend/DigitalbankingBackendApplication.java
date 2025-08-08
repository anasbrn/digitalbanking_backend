package org.digitalbanking.digitalbanking_backend;

import org.digitalbanking.digitalbanking_backend.entities.*;
import org.digitalbanking.digitalbanking_backend.enums.AccountStatus;
import org.digitalbanking.digitalbanking_backend.enums.OperationType;
import org.digitalbanking.digitalbanking_backend.repositories.BankAccountRepository;
import org.digitalbanking.digitalbanking_backend.repositories.CustomerRepository;
import org.digitalbanking.digitalbanking_backend.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalbankingBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner storeCustomers(
            CustomerRepository customerRepository,
            BankAccountRepository bankAccountRepository,
            OperationRepository operationRepository) {
        return args -> {
            Stream.of("Anas", "Ahmed", "Leila").forEach(name -> {
                Customer customer = Customer
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .name(name)
                        .email(name + "@gmail.com")
                        .build();

                customerRepository.save(customer);
            });

            List<Customer> customers = customerRepository.findAll();
            customers.forEach(customer -> {
                CurrentAccount currentAccount = CurrentAccount
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(new Date())
                        .balance(12000)
                        .currency("MAD")
                        .status(AccountStatus.CREATED)
                        .customer(customer)
                        .overDraft(5000)
                        .build();

                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = SavingAccount
                        .builder()
                        .id(UUID.randomUUID().toString())
                        .createdAt(new Date())
                        .balance(12000)
                        .currency("MAD")
                        .status(AccountStatus.CREATED)
                        .customer(customer)
                        .interestRate(2.5)
                        .build();
                bankAccountRepository.save(savingAccount);
            });

            List<BankAccount> bankAccounts = bankAccountRepository.findAll();
            bankAccounts.forEach(bankAccount -> {
                Operation operation = Operation
                        .builder()
                        .date(new Date())
                        .amount(3000)
                        .type(Math.random() > 0.5 ? OperationType.CREDIT : OperationType.WITHDRAW)
                        .bankAccount(bankAccount)
                        .build();

                operationRepository.save(operation);
            });
        };
    }
}
