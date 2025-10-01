package com.slotizen.venus.exception;

public class DepartmentValidationException extends RuntimeException {
    
    public DepartmentValidationException(String message) {
        super(message);
    }
    
    public DepartmentValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}