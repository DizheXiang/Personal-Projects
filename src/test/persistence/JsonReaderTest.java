package persistence;

import model.Receipt;
import model.ReceiptRecorder;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// citation URL: https://github.com/stleary/JSON-java.git
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ReceiptRecorder receiptRecorder = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyReceiptRecorder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyReceiptRecorder.json");
        try {
            ReceiptRecorder receiptRecorder = reader.read();
            assertEquals("My receipt recorder", receiptRecorder.getName());
            assertEquals(0, receiptRecorder.size());
            assertEquals(0.0, receiptRecorder.getBudget());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralReceiptRecorder() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralReceiptRecorder.json");
        try {
            ReceiptRecorder receiptRecorder = reader.read();
            ArrayList<Receipt> receipts = receiptRecorder.getReceipts();
            assertEquals(2, receipts.size());
            checkReceipts(50.0,"book", receipts.get(0));
            checkReceipts(10, "lunch", receipts.get(1));
            checkBudget(500,receiptRecorder);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
