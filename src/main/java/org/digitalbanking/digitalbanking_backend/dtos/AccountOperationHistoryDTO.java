package org.digitalbanking.digitalbanking_backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AccountOperationHistoryDTO {
    private String bankAccountId;
    private double balance;
    private String customerName;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private List<OperationDTO> operationDTOs;
}
