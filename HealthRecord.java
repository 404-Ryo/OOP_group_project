package com.mycompany.healthtrackerapp.records;

/**
 * HealthRecord - Base Abstract Class
 * -------------------------------------------------
 * Defines the common structure shared by all health records.
 *
 * OOP Concepts:
 *  - Abstraction: abstract class with abstract method displayRecord()
 *  - Encapsulation: 'date' is private, accessed via getter
 */
public abstract class HealthRecord {
    private String date;

    public HealthRecord(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    // Each subclass must provide its own way to display the record
    public abstract void displayRecord();
}
