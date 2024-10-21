package business.impl;

import business.PaymentService;
import dao.GymDAO;
import dao.PaymentDAO;
import dao.imp.GymDAOImp;
import dao.imp.PaymentDAOImp;
import entities.Payment;

import java.util.List;

public class PaymentServiceImp implements PaymentService {

    private final PaymentDAOImp paymentDAOImp;

    PaymentDAO paymentDAO = new PaymentDAOImp();

    public PaymentServiceImp() {
        this.paymentDAOImp = new PaymentDAOImp();
    }

    public PaymentServiceImp(PaymentDAOImp paymentDAOImp) {
        this.paymentDAOImp = paymentDAOImp;
    }

    @Override
    public Payment insert(Payment payment) {
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void updatePayment(Payment payment) {

    }

    @Override
    public Payment searchForId(Integer key) {
        return null;
    }

    @Override
    public boolean paymentExists(Integer key) {
        return false;
    }

    @Override
    public List<Payment> obtainAll() {
        return null;
    }
}
