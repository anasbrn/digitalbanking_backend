package org.digitalbanking.digitalbanking_backend.mappers;

import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.entities.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {
    public OperationDTO toDto(Operation operation) {
        if (operation == null) return null;
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(operation.getId());
        operationDTO.setDate(operation.getDate());
        operationDTO.setAmount(operation.getAmount());
        operationDTO.setType(operation.getType());
        return operationDTO;
    }

    public Operation toEntity(OperationDTO operationDTO) {
        if (operationDTO == null) return null;
        Operation operation = new Operation();
        operation.setId(operationDTO.getId());
        operation.setDate(operationDTO.getDate());
        operation.setAmount(operationDTO.getAmount());
        operation.setType(operationDTO.getType());
        return operation;
    }
}
