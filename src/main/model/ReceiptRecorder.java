package model;

public class ReceiptRecorder {
    private double amount;
    private String item;
    private String date;

    // EFFECTS: construct a new receipt recorder
    public ReceiptRecorder() {
        ReceiptRecorder receiptRecorder = new ReceiptRecorder();
    }

    public void addReceipt(double amount, String item, String date) {
        this.amount = amount;
        this.item = item;
        this.date = date;
    }

    public void findReceipt(Date date, String item) {
    }

    public void changeAmount(double newAmount) {
    }

    public void changeItem(String newItem) {
    }

    public void changeDate(Date newDate) {
    }

    public void removeReceipt() {
    }

    public double checkExpenses(Date startDate, Date endDate) {
        return 0.00;
    }
}
