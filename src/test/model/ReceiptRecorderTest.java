package model;

import model.exceptions.ReceiptDoesNotExistException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test methods in ReceiptRecorder class
public class ReceiptRecorderTest {
    ReceiptRecorder receiptRecorder;
    Receipt r1;

    @BeforeEach
    public void runBefore() {
        receiptRecorder = new ReceiptRecorder("My receipt recorder");
    }

    @Test
    public void testAddReceipt() {
        assertEquals(receiptRecorder.size(), 0);
        receiptRecorder.addReceipt(50.5, "textbook");
        assertEquals(receiptRecorder.size(), 1);
        receiptRecorder.addReceipt(100, "concert ticket");
        assertEquals(receiptRecorder.size(), 2);
    }

    @Test
    public void testFindReceiptWithoutException() {
        receiptRecorder.addReceipt(50.5, "textbook");
        try {
            r1 = receiptRecorder.findReceipt("textbook");
        } catch (ReceiptDoesNotExistException e) {
            fail("Unexpected exception");
        }
        assertEquals(r1.getAmount(), 50.5);
        assertEquals(r1.getItem(), "textbook");
    }

    @Test
    public void testFindReceiptWithException() {
        receiptRecorder.addReceipt(50.5, "textbook");
        try {
            r1 = receiptRecorder.findReceipt("book");
            fail();
        } catch (ReceiptDoesNotExistException e) {
            System.out.println("Sorry, the receipt doesn't exist");
        }
    }

    @Test
    public void testRemoveReceiptWithoutException() {
        try {
            receiptRecorder.addReceipt(50.5, "textbook");
            assertEquals(1, receiptRecorder.size());
            receiptRecorder.removeReceipt("textbook");
            assertEquals(0, receiptRecorder.size());
        } catch (ReceiptDoesNotExistException e) {
            fail("Unexpected exception");
        }

    }

    @Test
    public void testRemoveReceiptWithException() {
        try {
            receiptRecorder.removeReceipt("textbook");
            fail();
        } catch (ReceiptDoesNotExistException e) {
            System.out.println("Sorry, the receipt doesn't exist");
        }
    }

    @Test
    public void testSetBudget() {
        receiptRecorder.setBudget(1000.00);
        double amount = receiptRecorder.getBudget();
        assertEquals(1000.00, amount);
    }

    @Test
    public void testCheckExpenses() {
        receiptRecorder.addReceipt(50.5, "textbook");
        double amount = receiptRecorder.checkExpenses();
        assertEquals(amount, 50.5);
    }

    @Test
    public void testCheckBudget() {
        assertFalse(receiptRecorder.checkBudget());
        receiptRecorder.addReceipt(50.5, "textbook");
        assertTrue(receiptRecorder.checkBudget());
    }
}
