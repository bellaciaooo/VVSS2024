package pizzashop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTableNumber() {
        Payment payment = new Payment( 8,  PaymentType.Cash, 12.5);
        double total = payment.getTableNumber();
        assertEquals(total, 8);
    }

    @Test
    void getAmount() {
        Payment payment = new Payment( 8,  PaymentType.Cash, 12.5);
        double total = payment.getAmount();
        assertEquals(total, 12.5);
    }
}