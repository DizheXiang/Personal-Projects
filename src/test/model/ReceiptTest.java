package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test methods in Receipt class
public class ReceiptTest {
    Receipt r1;
    Receipt r2;

    @BeforeEach
    public void runBefore() {
        r1 = new Receipt(100.0, "concert ticket");
        r2 = new Receipt(10.0, "beef noodles");
    }

    @Test
    public void testFindAmount() {
        assertEquals(r1.findAmount(), 100.0);
        assertEquals(r2.findAmount(), 10.0);
    }

    @Test
    public void testFindItem() {
        assertEquals(r1.findItem(), "concert ticket");
        assertEquals(r2.findItem(), "beef noodles");
    }

    @Test
    public void testChangeAmount() {
        r1.changeAmount(10000);
        assertEquals(r1.findAmount(), 10000);
    }

    @Test
    public void testChangeItem() {
        r1.changeItem("house");
        assertEquals(r1.findItem(), "house");
    }

}
