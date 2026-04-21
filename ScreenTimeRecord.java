package com.mycompany.healthtrackerapp.records;

/**
 * ScreenTimeRecord - Mental Health Module
 * -------------------------------------------------
 * Records daily screen time to promote awareness of digital usage habits.
 *
 * OOP Concepts:
 *  - Inheritance: extends HealthRecord
 *  - Polymorphism: overrides displayRecord()
 *  - Encapsulation: 'screenTimeHours' is private
 */
public class ScreenTimeRecord extends HealthRecord {
    private double screenTimeHours;

    public ScreenTimeRecord(String date, double screenTimeHours) {
        super(date);
        this.screenTimeHours = screenTimeHours;
    }

    public double getScreenTimeHours() {
        return screenTimeHours;
    }

    public void setScreenTimeHours(double screenTimeHours) {
        this.screenTimeHours = screenTimeHours;
    }

    // Feedback based on 2-hour daily screen time threshold
    private String getFeedbackMessage() {
        if (screenTimeHours >= 2.0) {
            return "Your screen time is 2 hours or more. Try to reduce it tomorrow!";
        } else {
            return "Good pace! Your screen time is under 2 hours.";
        }
    }

    @Override
    public void displayRecord() {
        System.out.println("[Screen Time]");
        System.out.println("Date: " + getDate());
        System.out.println("Screen Time: " + screenTimeHours + " hours");
        System.out.println("Feedback: " + getFeedbackMessage());
    }
}
