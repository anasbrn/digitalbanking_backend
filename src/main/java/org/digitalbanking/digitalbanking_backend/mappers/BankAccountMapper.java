package org.digitalbanking.digitalbanking_backend.mappers;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.dtos.CurrentAccountDTO;
import org.digitalbanking.digitalbanking_backend.dtos.SavingAccountDTO;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.digitalbanking.digitalbanking_backend.entities.CurrentAccount;
import org.digitalbanking.digitalbanking_backend.entities.SavingAccount;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankAccountMapper {
    private final CustomerMapper customerMapper;

    public BankAccountDTO toDto(BankAccount bankAccount) {
        if (bankAccount instanceof CurrentAccount) {
            return toCurrentAccountDto((CurrentAccount) bankAccount);
        } else if (bankAccount instanceof SavingAccount) {
            return toSavingAccountDto((SavingAccount) bankAccount);
        } else {
            throw new IllegalArgumentException("Bank account type not supported, " + bankAccount.getClass().getName());
        }
    }

    private CurrentAccountDTO toCurrentAccountDto(CurrentAccount currentAccount) {
        if (currentAccount == null) return null;
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        mapCommonFields(currentAccountDTO, currentAccount);
        currentAccountDTO.setOverDraft(currentAccountDTO.getOverDraft());
        return currentAccountDTO;
    }

    private SavingAccountDTO toSavingAccountDto(SavingAccount savingAccount) {
        if (savingAccount == null) return null;
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        mapCommonFields(savingAccountDTO, savingAccount);
        savingAccountDTO.setInterestRate(savingAccount.getInterestRate());
        return savingAccountDTO;
    }

    private void mapCommonFields(BankAccountDTO dto, BankAccount account) {
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setCurrency(account.getCurrency());
        dto.setStatus(account.getStatus());
        dto.setCreatedAt(account.getCreatedAt());
        dto.setCustomer(customerMapper.toDto(account.getCustomer()));
    }
}
