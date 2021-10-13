package model;

public class Receipt {
    private double amount;
    private String date;
    private String item;

    public Receipt(double amount, String date, String item) {
        this.amount = amount;
        this.date = date;
        this.item = item;
    }
}
