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
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    public void insert(Payment payment) {
        paymentDAO.insert(payment);
    }

    @Override
    public void delete(Integer key) {
        paymentDAO.delete(key);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDAO.update(payment);
    }

    @Override
    public Payment searchForId(Integer key) {
        return null;
    }

    @Override
    public boolean paymentExists(Integer key) {
        return paymentDAO.paymentExists(key);
    }

    /**
     *
     * @param amount
     * @return
     */
    @Override
    public boolean checkPayment(Float amount, Payment payment) {

            try {
                if (amount == null) {
                    throw new NumberFormatException();
                }

                if(amount > 0) {
                    payment.setPaymentIsValid(true);
                    return true;
                } else if (amount <= 0) {
                    System.out.println("El monto debe ser mayor a 0. Intente nuevamente");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El dato ingresado no es un número válido. Intente nuevamente.");
                scanner.nextLine();
            }
        payment.setPaymentIsValid(false);
        return false;
    }

    @Override
    public List<Payment> listMemberPayments(Integer key) {
        List<Payment> memberPayments;
        memberPayments = paymentDAO.listMemberPayments(key);
        if(memberPayments.isEmpty()) {
            System.out.println("EL miembro no tiene pagos hechos.");
        }
        return memberPayments;
    }

    @Override
    public List<Payment> listGymPayments(int selectedGym) {
        List<Payment> gymPayments;
        gymPayments = paymentDAO.listGymPayments(selectedGym);
        return gymPayments;
    }

    @Override
    public List<Payment> listPaymentsByMethod(String method) {
        List<Payment> byMethodPayments;
        byMethodPayments = paymentDAO.listPaymentsByMethod(method);
        return byMethodPayments;
    }

    @Override
    public List<Payment> obtainAll() {
        List<Payment> payments;
        payments = paymentDAO.obtainAll();
        return payments;
    }

    @Override
    public LocalDate parsedDate(String parsingDate) {
        LocalDate parsedDate = null;

        while (parsedDate == null) {
            if (parsingDate == null || parsingDate.isEmpty()) {
                System.out.println("Fecha del pago: Hoy " + LocalDate.now());
                return LocalDate.now();
            }

            try {
                parsedDate = LocalDate.parse(parsingDate);
                System.out.println("Fecha OK");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR de sintaxis al ingresar fecha, recuerde que el formato es: 'AAAA-MM-DD'");
                System.out.println("Por favor, ingrese una fecha válida, o ENTER para que la fecha sea la de hoy");
                parsingDate = scanner.nextLine();
            }
        }

        return parsedDate;
    }

    @Override
    public void assignPaymentToAGym(Payment payment, int selectedGym) {
        paymentDAO.assignPaymentToAGym(payment, selectedGym);
    }
}
