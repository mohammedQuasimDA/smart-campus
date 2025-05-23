package com.sc.smartcampus.model;

import com.sc.smartcampus.exception.InvalidDepartmentCodeException;

public enum Department {
    // Core Engineering
    AEROSPACE("AE", "Aerospace Engineering"),
    AUTOMOBILE("AU", "Automobile Engineering"),
    BIOTECH("BT", "Biotechnology"),
    CHEMICAL("CH", "Chemical Engineering"),
    CIVIL("CE", "Civil Engineering"),
    COMPUTER_SCIENCE("CS", "Computer Science"),
    COMPUTER_ENGINEERING("CSE", "Computer Science Engineering"),
    ELECTRICAL("EE", "Electrical Engineering"),
    ELECTRONICS("EC", "Electronics Engineering"),
    ELECTRONICS_COMMUNICATION("ECE", "Electronics and Communication"),
    ENVIRONMENTAL("EN", "Environmental Engineering"),
    INDUSTRIAL("IE", "Industrial Engineering"),
    INFORMATION_TECH("IT", "Information Technology"),
    INSTRUMENTATION("IC", "Instrumentation and Control"),
    MECHANICAL("ME", "Mechanical Engineering"),
    MECHATRONICS("MT", "Mechatronics"),
    METALLURGY("ML", "Metallurgical Engineering"),
    MINING("MN", "Mining Engineering"),
    PETROLEUM("PE", "Petroleum Engineering"),
    PRODUCTION("PD", "Production Engineering"),
    ROBOTICS("RB", "Robotics Engineering"),
    TEXTILE("TE", "Textile Engineering"),
    ARTIFICIAL_INTELLIGENCE("AI", "Artificial Intelligence"),
    DATA_SCIENCE("DS", "Data Science"),
    CYBER_SECURITY("CY", "Cyber Security"),
    CLOUD_COMPUTING("CC", "Cloud Computing"),
    IOT("IO", "Internet of Things");

    private final String code;
    private final String fullName;

    Department(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    // Helper method to get department from code
    public static Department fromCode(String code) {
        for (Department dept : values()) {
            if (dept.code.equalsIgnoreCase(code)) {
                return dept;
            }
        }

        throw new InvalidDepartmentCodeException(code);
    }

}