package com.mycompany.healthtrackerapp;

import java.util.ArrayList;
import java.util.Scanner;

import com.mycompany.healthtrackerapp.records.ActivityRecord;
import com.mycompany.healthtrackerapp.records.DietRecord;
import com.mycompany.healthtrackerapp.records.HealthRecord;
import com.mycompany.healthtrackerapp.records.ScreenTimeRecord;

/**
 * HealthTrackerApp - Controller Module
 * -------------------------------------------------
 * Entry point of the application. Controls the execution flow and
 * handles user input / output through a simple console menu.
 *
 * OOP Concepts:
 *  - Polymorphism: Stores different record objects (DietRecord,
 *    ActivityRecord, ScreenTimeRecord) using a HealthRecord reference
 *    and invokes displayRecord() dynamically at runtime.
 */
public class HealthTrackerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<HealthRecord> records = new ArrayList<>();

        // Main application loop - runs until user chooses to exit
        while (true) {
            printMenu();
            int choice = readInt(sc, "Choose (1-6): ");

            switch (choice) {
                case 1 -> addDietRecord(sc, records);
                case 2 -> addStepRecord(sc, records);
                case 3 -> addScreenTimeRecord(sc, records);
                case 4 -> viewAllRecords(records);
                case 5 -> viewRecordsByDate(sc, records);
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-6.");
            }
        }
    }

    // ---------- Menu ----------
    private static void printMenu() {
        System.out.println("\n=== Health Tracker Menu ===");
        System.out.println("1. Add Diet Record (Intake & Burned + Supplements + Feedback)");
        System.out.println("2. Add Step Record");
        System.out.println("3. Add Screen Time Record");
        System.out.println("4. View All Records");
        System.out.println("5. View Records by Date");
        System.out.println("6. Exit");
    }

    // ---------- Add Records ----------
    private static void addDietRecord(Scanner sc, ArrayList<HealthRecord> records) {
        String date = readDate(sc);
        int intake = readInt(sc, "Enter calorie intake (kcal): ");
        int burned = readInt(sc, "Enter calories burned (kcal): ");
        boolean protein  = readYesNo(sc, "Did you take protein? (y/n): ");
        boolean creatine = readYesNo(sc, "Did you take creatine? (y/n): ");
        boolean vitamins = readYesNo(sc, "Did you take vitamins? (y/n): ");

        records.add(new DietRecord(date, intake, burned, protein, creatine, vitamins));
        System.out.println("Diet record added.");
    }

    private static void addStepRecord(Scanner sc, ArrayList<HealthRecord> records) {
        String date = readDate(sc);
        int steps = readInt(sc, "Enter steps: ");
        records.add(new ActivityRecord(date, steps));
        System.out.println("Step record added.");
    }

    private static void addScreenTimeRecord(Scanner sc, ArrayList<HealthRecord> records) {
        String date = readDate(sc);
        double hours = readDouble(sc, "Enter screen time (hours, e.g., 2.5): ");
        records.add(new ScreenTimeRecord(date, hours));
        System.out.println("Screen time record added.");
    }

    // ---------- View Records ----------
    private static void viewAllRecords(ArrayList<HealthRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n--- All Records ---");
        // Polymorphism in action: each record type displays itself correctly
        for (HealthRecord r : records) {
            r.displayRecord();
            System.out.println();
        }
    }

    private static void viewRecordsByDate(Scanner sc, ArrayList<HealthRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        String date = readDate(sc);
        boolean found = false;

        System.out.println("\n--- Records for " + date + " ---");
        for (HealthRecord r : records) {
            if (r.getDate().equals(date)) {
                r.displayRecord();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No records found for " + date + ".");
        }
    }

    // ---------- Input Helpers ----------
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) return true;
            if (line.equals("n") || line.equals("no"))  return false;
            System.out.println("Please enter y or n.");
        }
    }

    private static String readDate(Scanner sc) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        return sc.nextLine().trim();
    }
}
