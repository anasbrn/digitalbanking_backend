package org.digitalbanking.digitalbanking_backend.mappers;

import org.digitalbanking.digitalbanking_backend.dtos.AccountOperationHistoryDTO;
import org.digitalbanking.digitalbanking_backend.dtos.OperationDTO;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AccountOperationHistoryMapper {
    public AccountOperationHistoryDTO toDto(BankAccount bankAccount, Page<OperationDTO> operationDTOS, int currentPage) {
        AccountOperationHistoryDTO accountOperationHistoryDTO = new AccountOperationHistoryDTO();
        accountOperationHistoryDTO.setBankAccountId(bankAccount.getId());
        accountOperationHistoryDTO.setBalance(bankAccount.getBalance());
        accountOperationHistoryDTO.setCustomerName(bankAccount.getCustomer().getName());
        accountOperationHistoryDTO.setOperationDTOs(operationDTOS.getContent());
        accountOperationHistoryDTO.setCurrentPage(currentPage);
        accountOperationHistoryDTO.setTotalPages(operationDTOS.getTotalPages());
        accountOperationHistoryDTO.setPageSize(operationDTOS.getSize());

        return accountOperationHistoryDTO;
    }
}
