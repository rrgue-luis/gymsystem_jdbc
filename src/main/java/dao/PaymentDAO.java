package dao;

import entities.Payment;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface PaymentDAO extends DAO<Payment, Integer>{

    boolean paymentExists(Integer key);
    List<Payment> listMemberPayments(Integer key);

    List<Payment> listGymPayments(int selectedGym);

    List<Payment> listPaymentsByMethod(String method);

    void assignPaymentToAGym(Payment payment, int selectedGym);


}
