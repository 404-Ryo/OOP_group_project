package com.mycompany.healthtrackerapp.records;

/**
 * DietRecord - Diet Tracking Module
 * -------------------------------------------------
 * Manages dietary information: calorie intake, calories burned,
 * and supplement consumption (protein, creatine, vitamins).
 *
 * OOP Concepts:
 *  - Inheritance: extends HealthRecord
 *  - Polymorphism: overrides displayRecord()
 *  - Encapsulation: all fields are private
 */
public class DietRecord extends HealthRecord {
    private int caloriesIntake;
    private int caloriesBurned;

    private boolean proteinTaken;
    private boolean creatineTaken;
    private boolean vitaminsTaken;

    public DietRecord(String date, int caloriesIntake, int caloriesBurned,
                      boolean proteinTaken, boolean creatineTaken, boolean vitaminsTaken) {
        super(date);
        this.caloriesIntake = caloriesIntake;
        this.caloriesBurned = caloriesBurned;
        this.proteinTaken = proteinTaken;
        this.creatineTaken = creatineTaken;
        this.vitaminsTaken = vitaminsTaken;
    }

    // ---- Getters ----
    public int getCaloriesIntake() { return caloriesIntake; }
    public int getCaloriesBurned() { return caloriesBurned; }
    public boolean isProteinTaken() { return proteinTaken; }
    public boolean isCreatineTaken() { return creatineTaken; }
    public boolean isVitaminsTaken() { return vitaminsTaken; }

    // ---- Setters ----
    public void setCaloriesIntake(int caloriesIntake) { this.caloriesIntake = caloriesIntake; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }
    public void setProteinTaken(boolean proteinTaken) { this.proteinTaken = proteinTaken; }
    public void setCreatineTaken(boolean creatineTaken) { this.creatineTaken = creatineTaken; }
    public void setVitaminsTaken(boolean vitaminsTaken) { this.vitaminsTaken = vitaminsTaken; }

    // Calorie balance: positive means surplus, negative means deficit
    public int getCalorieBalance() {
        return caloriesIntake - caloriesBurned;
    }

    // Converts boolean to a user-friendly Yes/No string
    private String yesNo(boolean value) {
        return value ? "Yes" : "No";
    }

    // Simple textual feedback based on calorie balance
    private String getFeedbackMessage() {
        int balance = getCalorieBalance();
        if (balance > 0) {
            return "You consumed more calories than you burned. Try to burn more calories tomorrow!";
        } else if (balance < 0) {
            return "You burned more calories than you consumed. Keep up the good work!";
        } else {
            return "Your calorie intake and burn are balanced today.";
        }
    }

    @Override
    public void displayRecord() {
        System.out.println("[Diet Record]");
        System.out.println("Date: " + getDate());
        System.out.println("Calories Intake : " + caloriesIntake + " kcal");
        System.out.println("Calories Burned : " + caloriesBurned + " kcal");
        System.out.println("Balance         : " + getCalorieBalance() + " kcal");
        System.out.println("Protein Taken   : " + yesNo(proteinTaken));
        System.out.println("Creatine Taken  : " + yesNo(creatineTaken));
        System.out.println("Vitamins Taken  : " + yesNo(vitaminsTaken));
        System.out.println("Feedback        : " + getFeedbackMessage());
    }
}
