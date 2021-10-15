package model;

import java.util.ArrayList;

public class ReceiptRecorder {
    private final ArrayList<Receipt> receiptRecorder;
    private double budget;

    // EFFECTS: construct a new receipt recorder
    public ReceiptRecorder() {
        receiptRecorder = new ArrayList();
        this.budget = 0.0;
    }

    // MODIFIED: this
    // EFFECTS: add a receipt to the receipt recorder
    public void addReceipt(double amount, String item) {
        Receipt receipt = new Receipt(amount,  item);
        receiptRecorder.add(receipt);
    }

    // EFFECTS: find the receipt with given item
    public Receipt findReceipt(String item) {
        return new Receipt(0.0, "");
    }

    // MODIFIES: this
    // EFFECTS: find the receipt with given item
    public void removeReceipt(String item) {
    }

    // EFFECTS: change the budget amount
    public double changeBudget(double newAmount) {
        return budget;
    }

    // EFFECTS: produce the total spending amount
    public double checkExpenses() {
        return 0.00;
    }

    // EFFECTS: check whether total spending exceeds the budget
    public boolean checkBudget() {
        return false;
    }

    // EFFECTS: produce number of elements inside recorder
    public int size() {
        return receiptRecorder.size();
    }
}
