package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.exception.PizzaPaymentException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.validator.PaymentValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentRepositoryTestWithMock {
    @Mock
    private PaymentValidator paymentValidator;

    @InjectMocks
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        payment = mock(Payment.class);
    }

    @AfterEach
    void tearDown() {
        payment = null;
        paymentRepository = null;
    }

    @Test
    void add_invalid() {
        int nr = paymentRepository.getAll().size();
        Mockito.when(paymentValidator.validate(payment)).thenReturn(new ArrayList<>(Collections.singleton("abc")));
        try{
            paymentRepository.add(payment);
        }catch(PizzaPaymentException e){
            //e.printStackTrace();
            System.out.println("A trecut testul.");
        }
        assertEquals(nr, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, times(1)).validate(payment);
    }

    @Test
    void add_valid() {
        int nr = paymentRepository.getAll().size();
        Payment payment1 = new Payment(2,PaymentType.Cash,13);
        Mockito.when(paymentValidator.validate(payment1)).thenReturn(new ArrayList<>());

        try{
            paymentRepository.add(payment1);
        }catch(PizzaPaymentException e){
            e.printStackTrace();
            System.out.println("Nu a trecut testul.");
        }
        assertEquals(nr+1, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, times(1)).validate(payment1);
    }

}