package model;

import java.util.ArrayList;

// Records each receipt and has a budget
public class ReceiptRecorder {
    private final ArrayList<Receipt> receiptRecorder;
    private double budget;

    // EFFECTS: construct a new receipt recorder
    public ReceiptRecorder() {
        receiptRecorder = new ArrayList<>();
        this.budget = 0.00;
    }

    // MODIFIED: this
    // EFFECTS: add a receipt to the receipt recorder
    public void addReceipt(double amount, String item) {
        Receipt receipt = new Receipt(amount, item);
        receiptRecorder.add(receipt);
    }

    // EFFECTS: find the receipt with given item
    public Receipt findReceipt(String item) {
        Receipt trueReceipt = null;
        for (Receipt receipt : receiptRecorder) {
            if (receipt.getItem().equals(item)) {
                trueReceipt = receipt;
            }
        }
        return trueReceipt;
    }

    // EFFECTS: produce the total spending amount
    public double checkExpenses() {
        double totalAmount = 0.00;
        for (Receipt receipt : receiptRecorder) {
            totalAmount += receipt.getAmount();
        }
        return totalAmount;
    }

    // EFFECTS: check whether total spending exceeds the budget
    public boolean checkBudget() {
        double spending = checkExpenses();
        return spending > budget;
    }

    // MODIFIES: this
    // EFFECTS: remove the receipt with given item
    public void removeReceipt(String item) {
        Receipt changeObject = findReceipt(item);
        receiptRecorder.remove(changeObject);
    }

    // MODIFIES: this
    // EFFECTS: set the budget amount
    public double getBudget() {
        return budget;
    }

    // MODIFIES: this
    // EFFECTS: set the budget amount
    public void setBudget(double newAmount) {
        this.budget = newAmount;
    }

    // EFFECTS: return the size of receiptRecorder
    public int size() {
        return receiptRecorder.size();
    }
}
