package com.hospitalsystem.model;

/**
 * Member 3 - Doctor Record
 */
public class Doctor extends Person {

    private final String specializationField;
    private final double feeAmount;
    private final String workDays;

    public Doctor(String id, String name, int age, String specializationField, double feeAmount, String workDays) {
        super(id, name, age);
        this.specializationField = specializationField;
        this.feeAmount = feeAmount;
        this.workDays = workDays;
    }

    public String getSpecialization() {
        return specializationField;
    }

    public double getFee() {
        return feeAmount;
    }

    public String getAvailableDays() {
        return workDays;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Specialization: " + specializationField 
                + " | Fee: Rs." + feeAmount + " | Availability: " + workDays;
    }
}
