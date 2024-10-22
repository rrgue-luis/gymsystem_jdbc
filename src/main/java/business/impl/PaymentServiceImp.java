package business.impl;

import business.PaymentService;
import dao.GymDAO;
import dao.PaymentDAO;
import dao.imp.GymDAOImp;
import dao.imp.PaymentDAOImp;
import entities.Member;
import entities.Payment;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PaymentServiceImp implements PaymentService {

    Scanner scanner = new Scanner(System.in);
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

    /**
     *
     * @param amount
     * @return
     */
    @Override
    public boolean checkPayment(Float amount, Payment payment) {

            if (amount > 0) {
                payment.setPaymentIsValid(true);
                return true;
            } else {
                payment.setPaymentIsValid(false);
                System.out.println("El pago ingresado es menor a cero. Ingreselo nuevamente");
            }
            return false;

    }

    @Override
    public List<Payment> obtainAll() {
        return null;
    }

    @Override
    public LocalDate parsedDate(String parsingDate) {
        LocalDate parsedDate = null;

        while (parsedDate == null) {

            if (parsingDate == null || parsingDate.isEmpty()) {
                System.out.println("Por favor, ingrese una fecha en el formato correcto: 'AAAA-MM-DD'");
                parsingDate = scanner.nextLine();
            }

            try {
                parsedDate = LocalDate.parse(parsingDate);
                System.out.println("Fecha OK");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR de sintaxis al ingresar fecha, recuerde que el formato es: 'AAAA-MM-DD'");
                parsingDate = null;
            }
        }

        return parsedDate;
    }
}
