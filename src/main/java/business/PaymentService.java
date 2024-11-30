package business;

import entities.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    void insert(Payment payment);

    void delete(Integer key);

    void updatePayment(Payment payment);

    Payment searchForId(Integer key);

    boolean paymentExists(Integer key);

    /**
     * Revisa que la cantidad sea mayor a cero, NO revisa si alcanza o no
     * eso es trabajo de Membership.renewMembership();
     * @param amount
     * @param payment
     * @return true o false
     */
    boolean checkPayment(Float amount, Payment payment);

    List<Payment> listMemberPayments(Integer key);

    List<Payment> listGymPayments(int selectedGym);

    List<Payment> listPaymentsByMethod(String method);

    List<Payment> obtainAll();

    LocalDate parsedDate(String parsingDate);

    void assignPaymentToAGym(Payment payment, int selectedGym);

}
