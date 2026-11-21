package com.hospitalsystem.exception;

/**
 * Member 3 - Custom Exception for Patient Lookup Failure
 */
public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
