package org.digitalbanking.digitalbanking_backend.controllers;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.services.OperationServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
@AllArgsConstructor
public class OperationController {
    private final OperationServiceImpl operationServiceImpl;

    @GetMapping("/bankAccount/{id}")
    public Page<OperationDTO> findBankAccountById(
            @PathVariable(name = "id") String bankAccountId,
            @RequestParam(defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 2);
        return operationServiceImpl.findByBankAccountId(bankAccountId, pageable);
    }
}
