package com.sc.smartcampus.exception;

public class InvalidDepartmentCodeException extends RuntimeException {
  public InvalidDepartmentCodeException(String code) {
    super("Invalid department code: " + code);
  }
}
