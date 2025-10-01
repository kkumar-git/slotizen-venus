package com.slotizen.venus.exception;

public class DepartmentDeletionException extends RuntimeException {
    
    public DepartmentDeletionException(String message) {
        super(message);
    }
    
    public DepartmentDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}