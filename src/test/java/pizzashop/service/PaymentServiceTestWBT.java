package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTestWBT {
    private PaymentService srv;

    @BeforeEach
    void setUp() {
        PaymentRepository repopay = new PaymentRepository();
        MenuRepository repomenu = new MenuRepository();
        repopay.clear();
        srv = new PaymentService(repomenu,repopay);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalAmountCard() {
        srv.addPayment(8, PaymentType.Card, 10);
        double total = srv.getTotalAmount(PaymentType.Card);
        assertEquals(total, 10);
    }

    @Test
    void getTotalAmountCash() {
        srv.addPayment(3, PaymentType.Cash, 16);
        srv.addPayment(3, PaymentType.Card, 20);
        double total = srv.getTotalAmount(PaymentType.Cash);
        assertEquals(total, 16);
    }
    @Test
    void getTotalAmountMoreCash() {
        srv.addPayment(3, PaymentType.Cash, 16);
        srv.addPayment(3, PaymentType.Cash, 20);
        double total = srv.getTotalAmount(PaymentType.Cash);
        assertEquals(total, 36);
    }

    @Test
    void getTotalAmountNull() {
        srv.addPayment(8, PaymentType.Card, 10);
        double total = srv.getTotalAmount(null);
        assertEquals(total, 0);
    }
    @Test
    void getTotalAmountNull2() {
        double total = srv.getTotalAmount(PaymentType.Cash);
        assertEquals(total, 0);
    }


}