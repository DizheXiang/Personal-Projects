package model;

import model.Receipt;
import model.ReceiptRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptRecorderTest {
    ReceiptRecorder receiptRecorder;
    Receipt r1;

    @BeforeEach
    public void runBefore() {
        receiptRecorder = new ReceiptRecorder();
    }

    @Test
    public void testAddEach() {
        assertEquals(receiptRecorder.size(), 0);
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(receiptRecorder.size(), 1);
        receiptRecorder.addReceipt(100,"concert ticket");
        assertEquals(receiptRecorder.size(), 2);
    }

    @Test
    public void findReceipt() {
        receiptRecorder.addReceipt(50.5,"textbook");
        r1 = receiptRecorder.findReceipt("textbook");
        assertEquals(r1.findAmount(), 50.5);
        assertEquals(r1.findItem(), "textbook");
    }

    @Test
    public void testRemoveReceipt() {
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(receiptRecorder.size(), 1);
        receiptRecorder.removeReceipt("textbook");
        assertEquals(receiptRecorder.size(), 0);
    }

    @Test
    public void testCheckExpenses() {
        receiptRecorder.addReceipt(50.5,"textbook");
        double amount = receiptRecorder.checkExpenses();
        assertEquals(amount, 50.5);
    }

    @Test
    public void testCheckBudget() {
        assertTrue(receiptRecorder.checkBudget());
        receiptRecorder.addReceipt(50.5,"textbook");
        assertFalse(receiptRecorder.checkBudget());
    }

    @Test
    public void testSize() {
        assertEquals(receiptRecorder.size(), 0);
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(receiptRecorder.size(), 1);
    }
}
