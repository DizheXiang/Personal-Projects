package persistence;

import model.Receipt;
import model.ReceiptRecorder;

import static org.junit.jupiter.api.Assertions.assertEquals;

// citation URL: https://github.com/stleary/JSON-java.git
public class JsonTest {
    protected void checkReceipts(double amount, String item, Receipt receipt) {
        assertEquals(amount, receipt.getAmount());
        assertEquals(item, receipt.getItem());
    }
}
