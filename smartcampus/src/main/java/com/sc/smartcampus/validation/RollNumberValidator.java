package com.sc.smartcampus.validation;

import com.sc.smartcampus.model.Department;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RollNumberValidator implements ConstraintValidator<ValidRollNumber, String> {

    @Override
    public boolean isValid(String rollNumber, ConstraintValidatorContext context) {
        if (rollNumber == null || !rollNumber.matches("^\\d{2}[A-Z]{1,2}\\d{3}$")) {
            return false; // format is invalid
        }

        // extract department code
        String deptCode = rollNumber.substring(2, rollNumber.length() - 3);
        try {
            Department.fromCode(deptCode); // checks if dept code is valid
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
