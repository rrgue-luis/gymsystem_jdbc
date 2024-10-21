package dao;

import entities.Payment;

public interface PaymentDAO extends DAO<Payment, Integer>{

    boolean paymentExists(Integer key);

}
