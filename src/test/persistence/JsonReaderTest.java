package persistence;

import model.Receipt;
import model.ReceiptRecorder;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ReceiptRecorder receiptRecorder = reader.read();
            assertEquals(0, receiptRecorder.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            ReceiptRecorder receiptRecorder = reader.read();
            ArrayList<Receipt> receipts = receiptRecorder.receipts();
            assertEquals(2, receipts.size());
            checkReceipts(50.0,"book", receipts.get(0));
            checkReceipts(10, "lunch", receipts.get(1));
            checkBudget(500,receiptRecorder);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
