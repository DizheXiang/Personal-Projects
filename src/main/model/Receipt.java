package model;

// A receipt that contains amount of spending what the user bought
public class Receipt {
    private double amount;
    private String item;

    public Receipt(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    // EFFECTS: produce the amount of receipt
    public double getAmount() {
        return amount;
    }

    // EFFECTS: produce the item of receipt
    public String getItem() {
        return item;
    }

    // EFFECTS: change the amount of receipt
    public void changeAmount(double newAmount) {
        amount = newAmount;
    }

    // EFFECTS: change the item of receipt
    public void changeItem(String newItem) {
        item = newItem;
    }
}
