package com.hospitalsystem;

import com.hospitalsystem.exception.DoctorNotFoundException;
import com.hospitalsystem.exception.PatientNotFoundException;
import com.hospitalsystem.model.Appointment;
import com.hospitalsystem.model.Doctor;
import com.hospitalsystem.service.HospitalService;

import java.util.List;
import java.util.Scanner;

/**
 * Member 3 - Emergency Triage & Appointment Scheduling Console
 */
public class Main {

    private static final Scanner console = new Scanner(System.in);
    private static final HospitalService hospitalService = new HospitalService();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM - MEMBER 3 (TRIAGE) ");
        System.out.println("==================================================");

        boolean active = true;
        while (active) {
            printMenu();
            int opt = readPositiveInt("Select action (1-7): ");

            switch (opt) {
                case 1:
                    bookNewAppointment();
                    break;
                case 2:
                    displayAppointments();
                    break;
                case 3:
                    registerPatient();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    showDoctorsList();
                    break;
                case 6:
                    processPayment();
                    break;
                case 7:
                    active = false;
                    System.out.println("Shutting down triage console. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection, pick between 1 and 7.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- TRIAGE & APPOINTMENT DESK ---");
        System.out.println("1. Book Appointment (Regular or Emergency Triage)");
        System.out.println("2. View Appointment Schedule (Emergency at top)");
        System.out.println("3. Register New Patient");
        System.out.println("4. Add New Doctor");
        System.out.println("5. View Doctors (Sorted by fee)");
        System.out.println("6. Pay Appointment Bill");
        System.out.println("7. Exit System");
        System.out.println("---------------------------------");
    }

    private static void bookNewAppointment() {
        System.out.println("\n[Schedule Appointment]");
        String patientId = readString("Enter Patient ID: ");
        String doctorId = readString("Enter Doctor ID: ");
        String date = readString("Enter Date (YYYY-MM-DD): ");
        String emgResponse = readString("Emergency priority case? (y/n): ");
        boolean isEmergency = emgResponse.equalsIgnoreCase("y") || emgResponse.equalsIgnoreCase("yes");

        try {
            Appointment appt = hospitalService.bookAppointment(patientId, doctorId, date, isEmergency);
            if (isEmergency) {
                System.out.println("EMERGENCY ALERT: Case " + appt.getId() + " placed at position 1 in queue!");
            } else {
                System.out.println("Confirmed standard booking: " + appt.getId());
            }
        } catch (PatientNotFoundException | DoctorNotFoundException e) {
            System.out.println("Booking Rejected: " + e.getMessage());
        }
    }

    private static void displayAppointments() {
        System.out.println("\n[Scheduled Appointments (Triage Order)]");
        List<Appointment> list = hospitalService.getAppointments();
        if (list.isEmpty()) {
            System.out.println("Queue is currently empty.");
            return;
        }
        for (Appointment a : list) {
            System.out.println(a.getDetails());
        }
    }

    private static void registerPatient() {
        System.out.println("\n[Patient Registration]");
        String id = readString("Patient ID: ");
        String name = readString("Name: ");
        int age = readPositiveInt("Age: ");
        String condition = readString("Condition / Disease: ");

        try {
            hospitalService.registerPatient(id, name, age, condition);
            System.out.println("Patient " + name + " saved.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private static void addDoctor() {
        System.out.println("\n[Add Doctor]");
        String id = readString("Doctor ID: ");
        String name = readString("Name: ");
        int age = readPositiveInt("Age: ");
        String spec = readString("Specialization: ");
        double fee = readPositiveDouble("Consultation Fee: ");
        String days = readString("Visiting Days: ");

        try {
            hospitalService.addDoctor(id, name, age, spec, fee, days);
            System.out.println("Doctor registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private static void showDoctorsList() {
        System.out.println("\n[Doctors by Consultation Fee]");
        List<Doctor> docs = hospitalService.getDoctorsSortedByFee();
        if (docs.isEmpty()) {
            System.out.println("No doctors in registry.");
            return;
        }
        for (Doctor d : docs) {
            System.out.println(d.getDetails());
        }
    }

    private static void processPayment() {
        System.out.println("\n[Process Payment]");
        displayAppointments();
        String id = readString("Enter Appointment ID to settle: ");

        try {
            hospitalService.payBill(id);
            System.out.println("Payment recorded for " + id);
            System.out.println("Total Hospital Collections: Rs." + hospitalService.getTotalRevenue());
        } catch (IllegalArgumentException e) {
            System.out.println("Payment Error: " + e.getMessage());
        }
    }

    private static String readString(String label) {
        System.out.print(label);
        return console.nextLine().trim();
    }

    private static int readPositiveInt(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Integer.parseInt(console.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid integer.");
            }
        }
    }

    private static double readPositiveDouble(String label) {
        while (true) {
            System.out.print(label);
            try {
                return Double.parseDouble(console.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid number.");
            }
        }
    }
}
