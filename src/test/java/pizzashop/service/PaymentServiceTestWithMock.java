package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class PaymentServiceTestWithMock {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
        paymentService = null;
    }

    @Test
    void addPayment() {
        Payment payment = new Payment( 8,  PaymentType.Cash, 12.5);
        Payment payment2 = new Payment( 8,  PaymentType.Cash, 17);
        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(payment));

        paymentService.addPayment(payment2);
        assertEquals(1,paymentService.getPayments().size());

        Mockito.verify(paymentRepository, Mockito.times(1)).add(payment2);
    }

    @Test
    void getPayments() {
        Payment payment = new Payment( 8,  PaymentType.Cash, 14.5);
        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(payment));

        paymentService.addPayment(payment);
        Mockito.verify(paymentRepository, never()).getAll();
        Mockito.verify(paymentRepository, Mockito.times(1)).add(payment);

        assert 1 == paymentService.getPayments().size();
        Mockito.verify(paymentRepository, times(1)).getAll();

        assertEquals( 1 , paymentService.getPayments().size());
        Mockito.verify(paymentRepository, Mockito.times(2)).getAll();
    }
}