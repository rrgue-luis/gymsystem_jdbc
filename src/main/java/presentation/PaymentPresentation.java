package presentation;

import business.PaymentService;
import business.impl.PaymentServiceImp;
import entities.Payment;

import java.time.LocalDate;
import java.util.Scanner;

public class PaymentPresentation {

    Scanner scanner =  new Scanner(System.in);

    PaymentService paymentService = new PaymentServiceImp();

    public Payment insertMenu() {
        Payment payment = new Payment();

        System.out.println("Ingrese la cantidad del pago: ");
        payment.setAmount(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Ingrese la fecha (ENTER: FECHA ACTUAL)");
        String parsingDate = scanner.nextLine();

        if(parsingDate.equals("")) {
            System.out.println("Fecha del pago: Hoy " + LocalDate.now());
            payment.setPaymentDate(LocalDate.now());
        } else {
            LocalDate parsedDate = paymentService.parsedDate(parsingDate);
            System.out.println("Fecha asignada: " + parsedDate);
            payment.setPaymentDate(parsedDate);
        }

        String input = null;
        Payment.PaymentMethod paymentMethod = null;

        System.out.println("Ingrese el metodo de pago utilizado: ");

        while(paymentMethod == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                paymentMethod = Payment.PaymentMethod.valueOf(input);
                System.out.println("Tipo de pago elegido: " + paymentMethod);
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR");
            }
        }
        payment.setPaymentMethod(paymentMethod);

        //checkear que el pago sea suficiente y no negativo para la membresia.

        payment = paymentService.insert(payment);

        if (payment.getId() > 0) {
            System.out.println("Se creó correctamente el pago ID: " + payment.getId());
        } else {
            System.out.println("ERROR. No se creó el pago.");
        }

        return payment;
    }

}