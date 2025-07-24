package org.digitalbanking.digitalbanking_backend.services;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.entities.Operation;
import org.digitalbanking.digitalbanking_backend.mappers.OperationMapper;
import org.digitalbanking.digitalbanking_backend.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    @Override
    public Page<OperationDTO> findByBankAccountId(String bankAccountId, Pageable pageable) {
        Page<Operation> operations = operationRepository.findByBankAccountId(bankAccountId, pageable);
        return operations.map(operationMapper::toDto);
    }
}
