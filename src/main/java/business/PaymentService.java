package business;

import entities.Payment;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {

    Payment insert(Payment payment);

    void delete(Integer key);

    void updatePayment(Payment payment);

    Payment searchForId(Integer key);

    boolean paymentExists(Integer key);

    boolean checkPayment(Float amount, Payment payment);

    List<Payment> obtainAll();

    LocalDate parsedDate(String parsingDate);

}
