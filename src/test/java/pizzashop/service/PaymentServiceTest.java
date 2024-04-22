package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.exception.PizzaPaymentException;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceTest {
    private PaymentService serviceTest;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepositoryTest = new PaymentRepository();
        MenuRepository menuRepositoryTest = new MenuRepository();
        serviceTest = new PaymentService(menuRepositoryTest, paymentRepositoryTest);
    }

    @Disabled
    @AfterEach
    void tearDown() {
    }

    @Order(1)
    @RepeatedTest(value = 2)
    void addPaymentECP1() {
        int tableNo = 1;
        double amount = 17.5;
        //we check for the Cash amount
        double cashAmountBefore = serviceTest.getTotalAmount(PaymentType.Cash);
        serviceTest.addPayment(tableNo, PaymentType.Cash, amount);
        double cashAmountAfter = serviceTest.getTotalAmount(PaymentType.Cash);

        assertEquals(cashAmountBefore + amount, cashAmountAfter);
    }

    @Order(2)
    @Test
    void addPaymentECP2() {
        int tableNo = 5;
        double amount = 29;
        //we check for the Card amount
        double cardAmountBefore = serviceTest.getTotalAmount(PaymentType.Card);
        serviceTest.addPayment(tableNo, PaymentType.Card, amount);
        double cardAmountAfter = serviceTest.getTotalAmount(PaymentType.Card);

        assertEquals(cardAmountBefore + amount, cardAmountAfter);
    }

    @Order(3)
    @Test
    @Tag("InvalidTableNoTest")
    void addPaymentECP3() {
        int tableNoInvalid = 0;
        double amount = 12;
        assertThrows(PizzaPaymentException.class, () -> serviceTest.addPayment(tableNoInvalid, PaymentType.Cash, amount));
    }

    @Order(4)
    @Test
    @Tag("InvalidAmountTest")
    void addPaymentECP4() {
        int tableNo = 7;
        double amountInvalid = -44.5;
        assertThrows(PizzaPaymentException.class, () -> serviceTest.addPayment(tableNo, PaymentType.Cash, amountInvalid));
    }

    @Order(5)
    @Test
    @DisplayName("BVA - table number is 0 (invalid)")
    void addPaymentBVA1() {
        int tableNo = 0;
        double amount = 1;
        assertThrows(PizzaPaymentException.class, () -> serviceTest.addPayment(tableNo, PaymentType.Cash, amount));
    }

    @Order(6)
    @Test
    @DisplayName("BVA - table number is 1 (valid)")
    void addPaymentBVA2() {
        int tableNo = 1;
        double amount = 1;
        double cashAmountBefore = serviceTest.getTotalAmount(PaymentType.Cash);
        serviceTest.addPayment(tableNo, PaymentType.Cash, amount);
        double cashAmountAfter = serviceTest.getTotalAmount(PaymentType.Cash);

        assertEquals(cashAmountBefore + amount, cashAmountAfter);
    }

    @Order(7)
    @Test
    @DisplayName("BVA - amount is 0 (invalid)")
    void addPaymentBVA3() {
        int tableNo = 8;
        double amount = 0;
        assertThrows(PizzaPaymentException.class, () -> serviceTest.addPayment(tableNo, PaymentType.Cash, amount));
    }

    @Order(8)
    @Test
    @DisplayName("BVA - amount is 1 (valid)")
    void addPaymentBVA4() {
        int tableNo = 8;
        double amount = 1;
        double cashAmountBefore = serviceTest.getTotalAmount(PaymentType.Cash);
        serviceTest.addPayment(tableNo, PaymentType.Cash, amount);
        double cashAmountAfter = serviceTest.getTotalAmount(PaymentType.Cash);

        assertEquals(cashAmountBefore + amount, cashAmountAfter);
    }

}