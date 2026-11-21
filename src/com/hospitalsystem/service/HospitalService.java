package com.hospitalsystem.service;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.model.Patient;
import com.hospitalsystem.util.DoctorSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Member 3 - Service Implementation
 * Specializes in Appointment Scheduling with Emergency Triage Priority logic.
 */
public class HospitalService {

    private final Map<String, Patient> patientRecords = new HashMap<>();
    private final Map<String, Doctor> doctorRecords = new HashMap<>();
    private final List<Appointment> appointmentQueue = new ArrayList<>();

    private int nextBookingSequence = 101;

    public HospitalService() {
        // Pre-loaded seed doctors and patients
        doctorRecords.put("D1", new Doctor("D1", "Rajesh Sharma", 45, "Cardiologist", 800.0, "Mon, Wed, Fri"));
        doctorRecords.put("D2", new Doctor("D2", "Priya Patel", 38, "General Physician", 400.0, "Tue, Thu, Sat"));
        doctorRecords.put("D3", new Doctor("D3", "Amit Verma", 50, "Orthopedic", 600.0, "Mon, Tue, Thu"));

        patientRecords.put("P1", new Patient("P1", "Rohan Gupta", 30, "Fever & Cough"));
        patientRecords.put("P2", new Patient("P2", "Sneha Rao", 25, "Joint Pain"));
    }

    public void registerPatient(String id, String name, int age, String disease) {
        if (patientRecords.containsKey(id)) {
            throw new IllegalArgumentException("Patient with ID " + id + " already exists.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid age specified: " + age);
        }
        patientRecords.put(id, new Patient(id, name, age, disease));
    }

    public void addDoctor(String id, String name, int age, String specialization, double fee, String availableDays) {
        if (doctorRecords.containsKey(id)) {
            throw new IllegalArgumentException("Doctor with ID " + id + " already exists.");
        }
        if (fee < 0) {
            throw new IllegalArgumentException("Consultation fee cannot be negative.");
        }
        doctorRecords.put(id, new Doctor(id, name, age, specialization, fee, availableDays));
    }

    // Emergency triage scheduling algorithm
    public Appointment bookAppointment(String patientId, String doctorId, String date, boolean isEmergency)
            throws PatientNotFoundException, DoctorNotFoundException {

        Patient patient = patientRecords.get(patientId);
        if (patient == null) {
            throw new PatientNotFoundException("Patient ID " + patientId + " is not registered in system.");
        }

        Doctor doctor = doctorRecords.get(doctorId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor ID " + doctorId + " does not exist.");
        }

        String apptId = "APT" + (nextBookingSequence++);
        Appointment appt = new Appointment(apptId, patient, doctor, date, isEmergency);

        // Emergency Triage Priority: Inserts at index 0 to attend critical patients first
        if (isEmergency) {
            appointmentQueue.add(0, appt);
        } else {
            appointmentQueue.add(appt);
        }

        return appt;
    }

    public void payBill(String appointmentId) {
        for (Appointment a : appointmentQueue) {
            if (a.getId().equalsIgnoreCase(appointmentId)) {
                if (a.isPaid()) {
                    throw new IllegalArgumentException("Bill for " + appointmentId + " is already paid.");
                }
                a.markAsPaid();
                return;
            }
        }
        throw new IllegalArgumentException("Appointment ID " + appointmentId + " not found.");
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patientRecords.values());
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctorRecords.values());
    }

    public List<Doctor> getDoctorsSortedByFee() {
        List<Doctor> copy = new ArrayList<>(doctorRecords.values());
        DoctorSorter.sortByFeeAscending(copy);
        return copy;
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointmentQueue);
    }

    public double getTotalRevenue() {
        double total = 0.0;
        for (Appointment a : appointmentQueue) {
            if (a.isPaid()) {
                total += a.getAmount();
            }
        }
        return total;
    }
}
