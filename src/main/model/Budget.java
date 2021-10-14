package model;

public class Budget {
    private double amount;
    private model.Date startDate;
    private model.Date endDate;

    // EFFECTS: construct a budget
    public Budget(double amount, model.Date startDate) {
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = findEndDate(startDate);
    }

    private Date findEndDate(Date startDate) {
        return new Date(01,01,2021);
    }

    public void addBudget(String budgetAmount, Date startDate) {
    }

    public void changeAmount(double newAmount) {
    }

    public void changeItem(String newItem) {
    }

    public void changeStartDate(Date newStartDate) {
    }

    public void removeBudget() {
    }

    public boolean checkBudget() {
        return false;
    }
}
