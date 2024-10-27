package dao;

import entities.Payment;

import java.util.List;

public interface PaymentDAO extends DAO<Payment, Integer>{

    boolean paymentExists(Integer key);
    List<Payment> listMemberPayments(Integer key);

}
