package pizzashop.service;

import pizzashop.exception.PizzaPaymentException;
import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PaymentService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        Payment payment= new Payment(table, type, amount);
        if (table < 1 || table > 8)
            throw new PizzaPaymentException("Table number must be in [1,8]");
        if (amount <= 0)
            throw new PizzaPaymentException("Amount should be greater than 0");
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total = 0.0f;                 //1
        List<Payment> l=getPayments();
        if(type == null)                     //2
            return total;                    //3
        if (l == null)                       //4
            return total;                    //5
        if (l.size() == 0)                   //6
            return total;                    //7
        for (Payment p:l){                   //8
            if (p.getType().equals(type))    //9
                total+=p.getAmount();        //10
        }                                    //11
        return total;                        //12
    }                                        //13

}