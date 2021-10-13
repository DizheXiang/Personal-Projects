package model;

public class ReceiptRecorder {
    private double amount;
    private String item;
    private String date;

    public void addReceipt(double amount, String item, String date) {
        this.amount = amount;
        this.item = item;
        this.date = date;
    }

    public void findReceipt(String date, String item) {
    }

    public void changeAmount(double newAmount) {
    }

    public void changeItem(String newItem) {
    }

    public void changeDate(String newDate) {
    }

    public void removeReceipt() {
    }

    public double checkExpenses(String startDate, String endDate) {
        return 0.00;
    }
}
