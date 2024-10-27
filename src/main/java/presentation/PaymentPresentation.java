package presentation;

import business.PaymentService;
import business.impl.PaymentServiceImp;
import entities.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PaymentPresentation {

    Scanner scanner =  new Scanner(System.in);

    PaymentService paymentService = new PaymentServiceImp();

    /**
     * Funcion encargada de subir pagos a mano
     * ideal para solucionar problemas de caja.
     * @return devuelve un pago.
     */
    public Payment insertMenu() {

        Payment payment = new Payment();
        boolean checkPayment;
        float amount;
        do {
            System.out.println("Ingrese la cantidad del pago: ");
            amount = scanner.nextFloat();
            //checkeamos que el pago sea mayor que cero, si alcanza o no, se encarga memberservice
            checkPayment = paymentService.checkPayment(amount, payment);
        } while(!checkPayment);

        payment.setAmount(amount);

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

        System.out.println("Ingrese el metodo de pago utilizado ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT') ");

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

        System.out.println("Ingrese el ID del miembro que realizó el pago: ");
        payment.setMemberId(scanner.nextInt());

        payment = paymentService.insert(payment);

        if (payment.getId() > 0) {
            System.out.println("Se creó correctamente el pago ID: " + payment.getId());
        } else {
            System.out.println("ERROR. No se creó el pago.");
        }

        return payment;
    }

    /**
     * Muestra todos los pagos y los miembros a los que están asociados
     */
    public void obtainAllMenu() {

        List<Payment> payments = paymentService.obtainAll();
        for(Payment payment : payments) {
            System.out.println("---------------------");
            System.out.println("ID: " + payment.getId());
            System.out.println("ID Miembro: " + payment.getMemberId());
            System.out.println("Cantidad: " + payment.getAmount());
            System.out.println("Fecha: " + payment.getPaymentDate());
            System.out.println("Metodo: " + payment.getPaymentMethod());
            System.out.println("Valido: " + payment.PaymentIsValid());
        }

    }

    /**
     * Pide un ID de un pago y lo actualiza.
     */
    public void updateMenu() {

        System.out.println("Ingrese el ID del pago a modificar: ");
        int input = scanner.nextInt();
        boolean paymentExists = paymentService.paymentExists(input);

        if(paymentExists) {
            Payment payment = new Payment();

            payment.setId(input);
            System.out.println("Ingrese el ID del miembro que hizo el pago: ");
            payment.setMemberId(scanner.nextInt());

            System.out.println("Ingrese el monto: ");
            payment.setAmount(scanner.nextFloat());

            System.out.println("Ingrese la fecha del pago: (ENTER: FECHA ACTUAL)");
            String parsingDate = scanner.nextLine();

            if(parsingDate.equals("")) {
                System.out.println("Fecha del pago: Hoy " + LocalDate.now());
                payment.setPaymentDate(LocalDate.now());
            } else {
                LocalDate parsedDate = paymentService.parsedDate(parsingDate);
                System.out.println("Fecha asignada: " + parsedDate);
                payment.setPaymentDate(parsedDate);
            }

            String stringInput = null;
            Payment.PaymentMethod paymentMethod = null;
            System.out.println("Ingrese el metodo de pago utilizado ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT') ");

            while(paymentMethod == null) {

                stringInput = scanner.nextLine().trim().toUpperCase();

                try {
                    paymentMethod = Payment.PaymentMethod.valueOf(stringInput);
                    System.out.println("Tipo de pago elegido: " + paymentMethod);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR");
                }
            }


        }

    }

    /**
     * Pide un ID de un pago y lo elimina.
     */
    public void deleteMenu() {
        System.out.println("Ingrese el ID del pago a eliminar: ");
        obtainAllMenu();
        int option = scanner.nextInt();
        paymentService.delete(option);
        System.out.println("Pago borrado, ID: " + option);
    }

    public void listMemberPayments(){

        System.out.println("Ingrese el ID del miembro del que desea saber sus pagos: ");
        int input = scanner.nextInt();
        boolean paymentExists = paymentService.paymentExists(input);

        if(paymentExists) {
            List<Payment> memberPayments = paymentService.listMemberPayments(input);
            for(Payment payment : memberPayments) {
                System.out.println("---------------------");
                System.out.println("ID Miembro: " + payment.getMemberId());
                System.out.println("ID Pago: " + payment.getId());
                System.out.println("Cantidad: " + payment.getAmount());
                System.out.println("Fecha: " + payment.getPaymentDate());
                System.out.println("Método: " + payment.getPaymentMethod());
                System.out.println("Válido: " + payment.PaymentIsValid());
            }
        } else {
               System.out.println("No existe pago con ese ID, intente nuevamente: ");
        }




    }

}
