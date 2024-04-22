package pizzashop.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PaymentService;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PaymentTestSWithRWithoutE {
    Payment payment;
    PaymentRepository paymentRepository;
    PaymentService paymentService;


    @BeforeEach
    void setUp() {
        //Pentru E se folosesc obiecte mock
        payment = mock(Payment.class);
        paymentRepository = new PaymentRepository(new PaymentValidator());
        paymentService = new PaymentService(new MenuRepository(),paymentRepository);

        Mockito.when(payment.getPayment()).thenReturn(new Payment(2, PaymentType.Cash,12));
    }

    @AfterEach
    void tearDown() {
        payment = null;
    }

    @Test
    void getPayments() {
        int nr = paymentService.getPayments().size();
        assertEquals(nr, paymentService.getPayments().size());
        paymentRepository.add(payment.getPayment());
        assertEquals(nr + 1, paymentService.getPayments().size());

    }

    @Test
    void addPayment() {
        int nr = paymentService.getPayments().size();
        paymentRepository.add(payment.getPayment());
        assertEquals(payment.getPayment(),paymentService.getPayments().get(nr));
        assertEquals(nr + 1, paymentService.getPayments().size());
    }
}