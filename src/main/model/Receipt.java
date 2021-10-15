package model;

public class Receipt {
    private double amount;
    private String item;

    public Receipt(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    // EFFECTS: produce the amount of receipt
    public double findAmount() {
        return amount;
    }

    // EFFECTS: produce the item of receipt
    public String findItem() {
        return "";
    }

    // EFFECTS: change the amount of receipt
    public void changeAmount(double newAmount) {
    }

    // EFFECTS: change the item of receipt
    public void changeItem(String newItem) {
    }
}
