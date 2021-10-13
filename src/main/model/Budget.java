package model;

import java.util.Date;

public class Budget {
    private double amount;
    private String startDate;
    private String endDate;

    // EFFECTS: construct a budget
    public Budget(double amount, String startDate, String endDate) {
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addBudget(String budgetAmount, String startDate, String endDate) {
    }

    public void changeAmount(double newAmount) {
    }

    public void changeItem(String newItem) {
    }

    public void changeStartDate(String newStartDate) {
    }

    public void changeEndDate(String newEndDate) {
    }

    public void removeBudget() {
    }

    public boolean checkBudget() {
        return false;
    }
}
