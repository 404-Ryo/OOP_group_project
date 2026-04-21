package com.mycompany.healthtrackerapp.records;

/**
 * ActivityRecord - Activity Tracking Module
 * -------------------------------------------------
 * Records daily physical activity in terms of step count.
 *
 * OOP Concepts:
 *  - Inheritance: extends HealthRecord
 *  - Polymorphism: overrides displayRecord()
 *  - Encapsulation: 'steps' is private
 */
public class ActivityRecord extends HealthRecord {
    private int steps;

    public ActivityRecord(String date, int steps) {
        super(date);
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    // Provides simple feedback based on step count (10,000 step goal)
    private String getFeedbackMessage() {
        if (steps < 10000) {
            return "You walked less than 10,000 steps. Try to walk more tomorrow!";
        } else {
            return "Great job! You reached 10,000 steps or more today.";
        }
    }

    @Override
    public void displayRecord() {
        System.out.println("[Steps]");
        System.out.println("Date: " + getDate());
        System.out.println("Steps: " + steps);
        System.out.println("Feedback: " + getFeedbackMessage());
    }
}
