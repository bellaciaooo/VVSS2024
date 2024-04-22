package pizzashop.validator;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class PaymentValidator {
    public List<String> validate(Payment payment){
        List<String> errors = new ArrayList<>();
        if (payment.getAmount() < 0) errors.add("Amount can't be negative");
        if (payment.getTableNumber() <= 0 || payment.getTableNumber() >= 9) errors.add("Table no can't be out of [1,8]");
        if (payment.getType() != PaymentType.Cash && payment.getType() != PaymentType.Card) errors.add("Payment type can be only Cash or Card");
        return errors;
    }
}
