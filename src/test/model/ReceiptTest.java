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
        assertEquals(r1.getAmount(), 100.0);
        assertEquals(r2.getAmount(), 10.0);
    }

    @Test
    public void testFindItem() {
        assertEquals(r1.getItem(), "concert ticket");
        assertEquals(r2.getItem(), "beef noodles");
    }

    @Test
    public void testChangeAmount() {
        r1.changeAmount(10000);
        assertEquals(r1.getAmount(), 10000);
    }

    @Test
    public void testChangeItem() {
        r1.changeItem("house");
        assertEquals(r1.getItem(), "house");
    }

}
