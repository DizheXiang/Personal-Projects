package persistence;

import model.Receipt;
import model.ReceiptRecorder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkReceipts(double amount, String item, Receipt receipt) {
        assertEquals(amount, receipt.getAmount());
        assertEquals(item, receipt.getItem());
    }
}
