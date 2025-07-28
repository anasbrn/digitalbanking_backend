package org.digitalbanking.digitalbanking_backend.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.digitalbanking.digitalbanking_backend.annotations.ValidAccountType;
import org.digitalbanking.digitalbanking_backend.requests.BankAccountRequest;

public class AccountTypeValidator implements ConstraintValidator<ValidAccountType, BankAccountRequest> {
    @Override
    public boolean isValid(BankAccountRequest value, ConstraintValidatorContext context) {
        if (value.getType().equalsIgnoreCase("sa")) {
            return value.getInterestRate() != null;
        } else if (value.getType().equalsIgnoreCase("ca")) {
            return value.getOverDraft() != null;
        } else {
            return false;
        }
    }
}
