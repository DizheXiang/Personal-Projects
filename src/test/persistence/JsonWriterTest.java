package persistence;

import model.Receipt;
import model.ReceiptRecorder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            ReceiptRecorder receiptRecorder = new ReceiptRecorder("My receipt recorder");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ReceiptRecorder receiptRecorder = new ReceiptRecorder("My receipt recorder");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyReceiptRecorder.json");
            writer.open();
            writer.write(receiptRecorder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyReceiptRecorder.json");
            receiptRecorder = reader.read();
            assertEquals("My receipt recorder", receiptRecorder.getName());
            assertEquals(0, receiptRecorder.size());
            assertEquals(0.0, receiptRecorder.getBudget());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ReceiptRecorder receiptRecorder = new ReceiptRecorder("My receipt recorder");
            receiptRecorder.addReceipt(10,"lunch");
            receiptRecorder.addReceipt(50.0,"book");
            receiptRecorder.setBudget(500);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralReceiptRecorder.json");
            writer.open();
            writer.write(receiptRecorder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralReceiptRecorder.json");
            receiptRecorder = reader.read();
            assertEquals("My receipt recorder", receiptRecorder.getName());
            ArrayList<Receipt> receipts = receiptRecorder.getReceipts();
            assertEquals(2, receipts.size());
            checkReceipts(10,"lunch", receipts.get(0));
            checkReceipts(50.0,"book", receipts.get(1));
            assertEquals(500, receiptRecorder.getBudget());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
