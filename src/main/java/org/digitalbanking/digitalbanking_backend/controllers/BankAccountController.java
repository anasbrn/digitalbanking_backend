package org.digitalbanking.digitalbanking_backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.AccountOperationHistoryDTO;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.requests.BankAccountRequest;
import org.digitalbanking.digitalbanking_backend.services.BankAccountServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankAccounts")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountServiceImpl bankAccountServiceImpl;

    @GetMapping("/{id}/operations/history")
    public AccountOperationHistoryDTO findBankAccountById(
            @PathVariable(name = "id") String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        return bankAccountServiceImpl.findById(id, PageRequest.of(page, size));
    }

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
