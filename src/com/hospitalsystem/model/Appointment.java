package com.hospitalsystem.model;

/**
 * Member 3 - Appointment Model with Emergency Triage Priority
 */
public class Appointment {

    private final String appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private final String bookingDate;
    private final boolean emergencyCase;
    private boolean paidStatus;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String bookingDate, boolean emergencyCase) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.bookingDate = bookingDate;
        this.emergencyCase = emergencyCase;
        this.paidStatus = false;
    }

    public String getId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return bookingDate;
    }

    public boolean isEmergency() {
        return emergencyCase;
    }

    public double getAmount() {
        return doctor.getFee();
    }

    public boolean isPaid() {
        return paidStatus;
    }

    public void markAsPaid() {
        this.paidStatus = true;
    }

    public String getDetails() {
        String triageTag = emergencyCase ? "[PRIORITY-EMERGENCY]" : "[STANDARD]";
        return triageTag + " ID: " + appointmentId 
                + " | Patient: " + patient.getName() 
                + " | Doctor: Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")"
                + " | Date: " + bookingDate 
                + " | Fee: Rs." + getAmount() 
                + " | Payment: " + (paidStatus ? "PAID" : "UNPAID");
    }
}
