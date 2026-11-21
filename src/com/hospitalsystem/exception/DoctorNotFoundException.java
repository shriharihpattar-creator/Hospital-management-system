package com.hospitalsystem.exception;

/**
 * Member 3 - Custom Exception for Doctor Lookup Failure
 */
public class DoctorNotFoundException extends Exception {
    public DoctorNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
