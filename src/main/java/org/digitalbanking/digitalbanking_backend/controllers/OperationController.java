package org.digitalbanking.digitalbanking_backend.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.requests.CreditOperationRequest;
import org.digitalbanking.digitalbanking_backend.requests.TransferOperationRequest;
import org.digitalbanking.digitalbanking_backend.requests.WithdrawOperationRequest;
import org.digitalbanking.digitalbanking_backend.services.OperationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@AllArgsConstructor
public class OperationController {
    private final OperationServiceImpl operationServiceImpl;

    @GetMapping("/bankAccount/{id}")
    public Page<OperationDTO> findBankAccountById(
            @PathVariable(name = "id") String bankAccountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return operationServiceImpl.findByBankAccountId(bankAccountId, pageable);
    }

    @PostMapping("/credit")
    public void deposit(@RequestBody @Valid CreditOperationRequest request) {
        operationServiceImpl.deposit(request.getBankAccountId(), request.getAmount());
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody @Valid WithdrawOperationRequest request) {
        operationServiceImpl.withdraw(request.getBankAccountId(), request.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody @Valid TransferOperationRequest request) {
        operationServiceImpl.transfer(request.getSenderBankAccountId(), request.getRecipientBankAccountId(), request.getAmount());
    }
}
