package com.ciss.employee.exception;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException() {
        super("error-2", HttpStatus.NOT_FOUND);
    }
}
