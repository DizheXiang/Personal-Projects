package model;

public class Receipt {
    private double amount;
    private String item;
    private Date date;

    public Receipt(double amount, Date date, String item) {
        this.amount = amount;
        this.date = date;
        this.item = item;
    }
}
