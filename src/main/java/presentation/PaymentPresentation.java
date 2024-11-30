package presentation;

import business.GymService;
import business.PaymentService;
import business.impl.GymServiceImp;
import business.impl.PaymentServiceImp;
import dao.imp.GymDAOImp;
import entities.Payment;
import enums.payment.PaymentMethod;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentPresentation {

    Scanner scanner =  new Scanner(System.in);

    PaymentService paymentService = new PaymentServiceImp();
    GymService gymService = new GymServiceImp();

    /**
     * Funcion encargada de subir pagos a mano
     * ideal para solucionar problemas de caja.
     * @return devuelve un pago.
     */
    public void insertMenu(int selectedGym) {
        int exit = 1;
        while(exit != 0) {

            setSelectedGym(selectedGym);
            Payment payment = new Payment();

            float amount = 0;
            boolean inputIsValid = false;
            do {

                System.out.println("Ingrese la cantidad del pago: ");

                try {
                    amount = scanner.nextFloat();
                    if(paymentService.checkPayment(amount, payment)) {
                        System.out.println("Pago OK");
                        inputIsValid = true;
                    } else {
                        System.out.println("El pago ingresado no es válido, intente nuevamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error. El dato ingresado no es un número. Intente nuevamente");
                    scanner.nextLine();
                }

            } while(!inputIsValid);

            payment.setAmount(amount);
            scanner.nextLine();

            System.out.println("Ingrese la fecha (ENTER: FECHA ACTUAL)");
            String parsingDate = scanner.nextLine();

            if (parsingDate.equals("")) {
                System.out.println("Fecha del pago: Hoy " + LocalDate.now());
                payment.setPaymentDate(LocalDate.now());
            } else {
                LocalDate parsedDate = paymentService.parsedDate(parsingDate);
                System.out.println("Fecha asignada: " + parsedDate);
                payment.setPaymentDate(parsedDate);
            }

            String input = null;
            PaymentMethod paymentMethod = null;
            System.out.println("Ingrese el metodo de pago utilizado ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT') ");

            while (paymentMethod == null) {
                input = scanner.nextLine().trim().toUpperCase();

                try {
                    paymentMethod = PaymentMethod.valueOf(input);
                    System.out.println("Tipo de pago elegido: " + paymentMethod);
                } catch (IllegalArgumentException e) {
                    System.out.println("ERROR: Metodo no reconocido. Intente nuevamente ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT')");
                }
            }
            payment.setPaymentMethod(paymentMethod);

            System.out.println("Ingrese el ID del miembro que realizó el pago: ");
            payment.setMemberId(scanner.nextInt());
            paymentService.insert(payment);
            paymentService.assignPaymentToAGym(payment, selectedGym);

            if (payment.getId() > 0) {
                System.out.println("Se creó correctamente el pago ID: " + payment.getId() + " Al gimnasio: " + gymService.showName(selectedGym));
            } else {
                System.out.println("ERROR. No se creó el pago.");
            }
            System.out.println("\nPulse 0 para salir, o cualquier numero para seguir agregando pagos.");
            try {
                exit = scanner.nextInt();
                if(exit == 0) {
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Saliendo...");
            }
        }
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
            PaymentMethod paymentMethod = null;
            System.out.println("Ingrese el metodo de pago utilizado ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT') ");

            while(paymentMethod == null) {

                stringInput = scanner.nextLine().trim().toUpperCase();

                try {
                    paymentMethod = PaymentMethod.valueOf(stringInput);
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
        System.out.println("Ingrese el ID del pago a eliminar, 0 para salir: ");
        obtainAllMenu();
        int input = -1;
        do {

            try {
                System.out.print("ID del pago a eliminar, 0 para salir: ");
                input = scanner.nextInt(); // Leer el input

                if (input == 0) {
                    System.out.println("Saliendo del menú de eliminación de pagos.");
                    break;
                }

                if (paymentService.paymentExists(input)) {
                    System.out.println("Pago encontrado: " + paymentService.searchForId(input));
                    paymentService.delete(input); // Eliminar el pago
                    System.out.println("Pago eliminado exitosamente.");
                } else {
                    System.out.println("El pago no existe, intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: El dato ingresado no es un número válido. Intente de nuevo.");
                scanner.nextLine();
            }
        } while (true);

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
                System.out.println("ID Gym: " + payment.getGymId());
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

    public void listGymPayments(int selectedGym) {

        GymService gymService = new GymServiceImp();

        setSelectedGym(selectedGym);

            List<Payment> gymPayments = paymentService.listGymPayments(selectedGym);
            for (Payment payment : gymPayments) {
                System.out.println("---------------------");
                System.out.println("ID Miembro: " + payment.getMemberId());
                System.out.println("ID Gym: " + payment.getGymId());
                System.out.println("ID Pago: " + payment.getId());
                System.out.println("Cantidad: " + payment.getAmount());
                System.out.println("Fecha: " + payment.getPaymentDate());
                System.out.println("Método: " + payment.getPaymentMethod());
                System.out.println("Válido: " + payment.PaymentIsValid());
            }
    }

    public void listPaymentsByMethod() {
        System.out.println("------ORDENAR POR TIPO DE PAGO------");

        String method = null;
        PaymentMethod paymentMethod = null;

        System.out.println("Ingrese el tipo de pago por el cual ordenar: ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT')");

        while(paymentMethod == null) {
            method = scanner.nextLine().trim().toUpperCase();

            try {
                paymentMethod = PaymentMethod.valueOf(method);
                System.out.println("Método elegido: CASH");
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de pago invalido, recibido: " + method + " Esperado: ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT')");
            }

        }

        List<Payment> paymentsByMethod = paymentService.listPaymentsByMethod(method);
        for (Payment payment : paymentsByMethod) {
            System.out.println("---------------------");
            System.out.println("Método: " + payment.getPaymentMethod());
            System.out.println("ID Miembro: " + payment.getMemberId());
            System.out.println("ID Gym: " + payment.getGymId());
            System.out.println("ID Pago: " + payment.getId());
            System.out.println("Cantidad: " + payment.getAmount());
            System.out.println("Fecha: " + payment.getPaymentDate());
            System.out.println("Válido: " + payment.PaymentIsValid());

        }
    }

    /**
     * Muestra toda la presentacion de setear el
     * gimnasio actual, permitiendo cambiarlo o
     * simplemente apretar enter para mantener el gimnasio
     * por defecto desde el main
     * @param selectedGym seteado desde el main
     */
    public void setSelectedGym(int selectedGym) {
        System.out.print("Ingrese el ID del gym (o presione Enter para mantener el actual): ");
        String inputString;
        boolean inputIsValid = false;
        do {
            inputString = scanner.nextLine().trim();

            if (inputString.equals("")) {
                if(!gymService.gymExists(selectedGym)) {
                    System.out.println("ERROR: El gimnasio no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                } else {
                    System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                    inputIsValid = true;
                }
            } else {
                try {
                    int gymId = Integer.parseInt(inputString);
                    if(!gymService.gymExists(gymId)) {
                        System.out.println("EL gimnasio ingresado no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                    } else {
                        selectedGym = gymId;
                        System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                        inputIsValid = true;
                    }
                } catch (NumberFormatException e){
                    System.out.println("Error: EL dato ingresado no es un numero valido. Intente nuevamente con un numero o ENTER para mantener el actual");

                }
            }

        }while (!inputIsValid);
    }

}
