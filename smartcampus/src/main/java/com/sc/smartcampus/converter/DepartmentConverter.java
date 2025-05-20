package com.sc.smartcampus.converter;

import com.sc.smartcampus.model.Department;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {

    @Override
    public String convertToDatabaseColumn(Department department) {
        if (department == null) return null;
        return department.getCode(); // store "ME" instead of "MECHANICAL"
    }

    @Override
    public Department convertToEntityAttribute(String code) {
        if (code == null) return null;
        return Department.fromCode(code); // convert "ME" back to MECHANICAL
    }
}
