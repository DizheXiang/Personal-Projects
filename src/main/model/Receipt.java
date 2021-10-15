package model;

public class Receipt {
    private double amount;
    private String item;

    public Receipt(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    public double findAmount() {
        return amount;
    }

    public String findItem() {
        return "";
    }

    public void changeAmount(double newAmount) {
    }

    public void changeItem(String newItem) {
    }
}
