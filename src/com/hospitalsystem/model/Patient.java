package com.hospitalsystem.model;

/**
 * Member 3 - Patient Record with Medical History
 */
public class Patient extends Person {

    private final String medicalCondition;

    public Patient(String id, String name, int age, String medicalCondition) {
        super(id, name, age);
        this.medicalCondition = medicalCondition;
    }

    public String getDisease() {
        return medicalCondition;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Condition: " + medicalCondition;
    }
}
