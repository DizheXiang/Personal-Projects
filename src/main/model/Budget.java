package model;


public class Budget {
    private double amount;
    private Date startDate;
    private Date endDate;

    // EFFECTS: construct a budget
    public Budget(double amount, Date startDate) {
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = findEndDate(startDate);
    }

    // MODIFIES: this
    // EFFECTS: produce the end date after a month after given start date
    public Date findEndDate(Date startDate) {
        return new Date(1,1,2021);
    }

    // MODIFIES: this
    // EFFECTS: create a budget given by amount and start date of budget
    public void addBudget(String budgetAmount, Date startDate) {
    }

    // MODIFIES: this
    // EFFECTS: change the amount of budget
    public void changeAmount(double newAmount) {
    }

    // MODIFIES: this
    // EFFECTS: change the start date of budget
    public void changeStartDate(Date newStartDate) {
    }

    // MODIFIES: this
    // EFFECTS: remove a budget
    public void removeBudget() {
    }

    // EFFECTS: check whether the budget is reached
    public boolean checkBudget() {
        return false;
    }
}
