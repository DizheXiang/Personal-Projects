package model;

import java.util.ArrayList;

public class ReceiptRecorder {
    private final ArrayList<Receipt> receiptRecorder;

    // EFFECTS: construct a new receipt recorder
    public ReceiptRecorder() {
        receiptRecorder = new ArrayList();
    }

    // MODIFIED: this
    //EFFECTS: add a receipt to the receipt recorder
    public void addReceipt(double amount, Date date, String item) {
        Receipt receipt = new Receipt(amount, date, item);
        receiptRecorder.add(receipt);
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
