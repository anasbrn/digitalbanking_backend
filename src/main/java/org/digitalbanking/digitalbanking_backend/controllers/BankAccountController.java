package org.digitalbanking.digitalbanking_backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.requests.BankAccountRequest;
import org.digitalbanking.digitalbanking_backend.services.BankAccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccounts")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountServiceImpl bankAccountServiceImpl;

    @GetMapping("/{id}")
    public BankAccountDTO findBankAccountById(@PathVariable(name = "id") String id) {
        return bankAccountServiceImpl.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<BankAccountDTO> saveBankAccount(@RequestBody @Valid BankAccountRequest request) {
        BankAccountDTO savedBankAccountDTO = bankAccountServiceImpl.saveBankAccount(request);
        return new ResponseEntity<>(savedBankAccountDTO, HttpStatus.CREATED);
    }
}
