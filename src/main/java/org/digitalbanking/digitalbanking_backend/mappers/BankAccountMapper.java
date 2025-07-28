package org.digitalbanking.digitalbanking_backend.mappers;

import lombok.AllArgsConstructor;
import org.digitalbanking.digitalbanking_backend.dtos.BankAccountDTO;
import org.digitalbanking.digitalbanking_backend.dtos.CurrentAccountDTO;
import org.digitalbanking.digitalbanking_backend.dtos.SavingAccountDTO;
import org.digitalbanking.digitalbanking_backend.entities.BankAccount;
import org.digitalbanking.digitalbanking_backend.entities.CurrentAccount;
import org.digitalbanking.digitalbanking_backend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
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

    public CurrentAccountDTO toCurrentAccountDto(CurrentAccount currentAccount) {
        if (currentAccount == null) return null;
        CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentAccountDTO);
        currentAccountDTO.setCustomer(this.customerMapper.toDto(currentAccount.getCustomer()));
        currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
        currentAccountDTO.setOverDraft(currentAccount.getOverDraft());
        return currentAccountDTO;
    }

    public SavingAccountDTO toSavingAccountDto(SavingAccount savingAccount) {
        if (savingAccount == null) return null;
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingAccountDTO);
        savingAccountDTO.setCustomer(this.customerMapper.toDto(savingAccount.getCustomer()));
        savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
        savingAccountDTO.setInterestRate(savingAccount.getInterestRate());
        return savingAccountDTO;
    }

//    public BankAccount toEntity(BankAccountDTO bankAccountDTO) {
//        if (bankAccountDTO instanceof CurrentAccountDTO) {
//            return toCurrentAccountEntity((CurrentAccountDTO) bankAccountDTO);
//        } else if (bankAccountDTO instanceof SavingAccountDTO) {
//            return toSavingAccountEntity((SavingAccountDTO) bankAccountDTO);
//        } else {
//            throw new IllegalArgumentException("Bank account dto type not supported, " + bankAccountDTO.getClass().getName());
//        }
//    }

//    public CurrentAccount toCurrentAccountEntity(CurrentAccountDTO currentAccountDTO) {
//        if (currentAccountDTO == null) return null;
//        CurrentAccount currentAccount = new CurrentAccount();
//        mapCommonFieldsEntity(currentAccount, currentAccountDTO);
//        currentAccount.setOverDraft(currentAccountDTO.getOverDraft());
//        return currentAccount;
//    }
//
//    public SavingAccount toSavingAccountEntity(SavingAccountDTO savingAccountDTO) {
//        if (savingAccountDTO == null) return null;
//        SavingAccount savingAccount = new SavingAccount();
//        mapCommonFieldsEntity(savingAccount, savingAccountDTO);
//        savingAccount.setInterestRate(savingAccountDTO.getInterestRate());
//        return savingAccount;
//    }
}
