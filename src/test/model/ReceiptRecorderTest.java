package model;

import model.exceptions.ReceiptDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test methods in ReceiptRecorder class
public class ReceiptRecorderTest {
    ReceiptRecorder receiptRecorder;
    Receipt r1;

    @BeforeEach
    public void runBefore() {
        receiptRecorder = new ReceiptRecorder();
    }

    @Test
    public void testAddReceipt() {
        assertEquals(receiptRecorder.size(), 0);
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(receiptRecorder.size(), 1);
        receiptRecorder.addReceipt(100,"concert ticket");
        assertEquals(receiptRecorder.size(), 2);
    }

    @Test
    public void testFindReceipt() {
        receiptRecorder.addReceipt(50.5,"textbook");
        try {
            r1 = receiptRecorder.findReceipt("textbook");
        } catch (ReceiptDoesNotExistException e) {
            System.out.println("Sorry, the receipt doesn't exist");
        }
        assertEquals(r1.getAmount(), 50.5);
        assertEquals(r1.getItem(), "textbook");
    }

    @Test
    public void testRemoveReceipt() {
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(1, receiptRecorder.size());
        receiptRecorder.removeReceipt("textbook");
        assertEquals(0, receiptRecorder.size());
    }

    @Test
    public void testGetBudget() {
        assertEquals(0.00, receiptRecorder.getBudget());
        receiptRecorder.setBudget(1000.00);
        assertEquals(1000.00, receiptRecorder.getBudget());
    }

    @Test
    public void testSetBudget() {
        receiptRecorder.setBudget(1000.00);
        double amount = receiptRecorder.getBudget();
        assertEquals(1000.00, amount);
    }

    @Test
    public void testCheckExpenses() {
        receiptRecorder.addReceipt(50.5,"textbook");
        double amount = receiptRecorder.checkExpenses();
        assertEquals(amount, 50.5);
    }

    @Test
    public void testCheckBudget() {
        assertFalse(receiptRecorder.checkBudget());
        receiptRecorder.addReceipt(50.5,"textbook");
        assertTrue(receiptRecorder.checkBudget());
    }

    @Test
    public void testSize() {
        assertEquals(receiptRecorder.size(), 0);
        receiptRecorder.addReceipt(50.5,"textbook");
        assertEquals(receiptRecorder.size(), 1);
    }
}
