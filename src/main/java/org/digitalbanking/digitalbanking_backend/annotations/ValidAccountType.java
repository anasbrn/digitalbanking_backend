package org.digitalbanking.digitalbanking_backend.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.digitalbanking.digitalbanking_backend.validators.AccountTypeValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountTypeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAccountType {
    String message() default "InterestRate field is required in Saving Account & OverDraft field is required in Current Account";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
