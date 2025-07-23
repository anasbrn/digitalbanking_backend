package org.digitalbanking.digitalbanking_backend.controllers;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.services.BankAccountServiceImpl;
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
}
