package com.sc.smartcampus.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RollNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRollNumber {
    String message() default "Invalid roll number or department code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
