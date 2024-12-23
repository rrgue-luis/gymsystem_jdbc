package presentation;

import business.MemberService;
import business.MembershipService;
import business.PaymentService;
import business.impl.MemberServiceImp;
import business.impl.MembershipServiceImp;
import business.impl.PaymentServiceImp;
import entities.Member;
import entities.Payment;
import enums.member.MembershipType;
import enums.payment.PaymentMethod;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MembershipPresentation {

    Scanner scanner = new Scanner(System.in);
    MemberService memberService = new MemberServiceImp();
    PaymentService paymentService = new PaymentServiceImp();
    MembershipService membershipService = new MembershipServiceImp();
    MemberPresentation memberPresentation = new MemberPresentation();

    public void renewMembership(int selectedGym) {

        selectedGym = memberPresentation.setSelectedGym(selectedGym);

        System.out.println("Ingrese el ID del miembro a renovar: (--0-- PARA VOLVER AL MENÚ)");
        boolean inputIsValid = false;

        do {
            String inputString = scanner.nextLine().trim();

            if(inputString.equals("")) {
                System.out.println("No ingresó ninguna opción, --0-- para salir");
            } else {
                try {
                    int input = Integer.parseInt(inputString);
                    if(input == 0) {
                        System.out.println("Saliendo...");
                        break;
                    }
                    boolean memberExists = memberService.memberExists(input);

                    String parsingDate;
                    LocalDate parsedDate;
                    do {
                        System.out.println("Ingrese la fecha de renovación: (ENTER: FECHA ACTUAL)");
                        parsingDate = scanner.nextLine();

                        if(parsingDate.equals("")) {
                            System.out.println("Fecha de renovación: Hoy " + LocalDate.now());
                            parsedDate = LocalDate.now();
                            break;
                        } else {
                            try {
                                parsedDate = LocalDate.parse(parsingDate);
                                System.out.println("Fecha de renovación asignada: " + parsingDate);
                                break;
                            } catch(DateTimeParseException e){
                                System.out.println("Error de syntaxis, recuerde que el formato es: 'AAAA-MM-DD'");
                            }
                        }
                    } while (true);


                    if (memberExists) {
                        Member member = memberService.searchForId(input);
                        member.setId(input);

                        System.out.println("Ingrese el tipo de membresía: 'DAILY', 'WEEKLY', 'MONTHLY' ");
                        MembershipType membershipType = null;

                        while(membershipType == null) {

                            inputString = scanner.nextLine().toUpperCase();

                            try {
                                membershipType = MembershipType.valueOf(inputString);
                                System.out.println("Membresía elegida: " + membershipType);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Tipo de membresía no valido. Intente nuevamente ('DAILY', 'WEEKLY', 'MONTHLY'):");
                            }

                        }
                        member.setMembershipType(membershipType);
                        LocalDate memberShipEndDate = memberService.membershipEndDate(member, parsedDate);
                        member.setMembershipEndDate(memberShipEndDate);
                        /*
                        SI LLEGÓ ACÁ EL MIEMBRO ESTÁ OK,
                        CREACION DEl PAGO
                         */
                        Payment payment = new Payment();

                        payment.setGymId(selectedGym);

                        boolean checkPayment = false;
                        float amount = 0;

                        while (!checkPayment) {
                            try {
                                System.out.println("Ingrese la cantidad del pago: ");
                                amount = scanner.nextFloat();
                                if(amount <= 0) {
                                    System.out.println("El monto ingresado es menor a 0, intente nuevamente");

                                }
                            } catch (InputMismatchException e) {
                                System.out.println("El dato ingresado no es numero valido, intente nuevamente.");
                            }
                            checkPayment = paymentService.checkPayment(amount, payment);
                            payment.setAmount(amount);
                            scanner.nextLine();
                        }

                        System.out.println("Ingrese la fecha del pago. (ENTER: FECHA ACTUAL)");
                        parsingDate = scanner.nextLine();

                        if (parsingDate.equals("")) {
                            System.out.println("Fecha del pago: Hoy " + LocalDate.now());
                            payment.setPaymentDate(LocalDate.now());
                        } else {
                            parsedDate = paymentService.parsedDate(parsingDate);
                            System.out.println("Fecha asignada: " + parsedDate);
                            payment.setPaymentDate(parsedDate);
                        }

                        String method;
                        PaymentMethod paymentMethod = null;
                        System.out.println("Ingrese el metodo de pago utilizado ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT') ");

                        while (paymentMethod == null) {

                            method = scanner.nextLine().trim().toUpperCase();

                            try {
                                paymentMethod = PaymentMethod.valueOf(method);
                                System.out.println("Tipo de pago elegido: " + paymentMethod);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error de syntaxis, elija una opcion valida ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT')");
                            }
                        }
                        payment.setPaymentMethod(paymentMethod);
                        payment.setMemberId(member.getId());
                        membershipService.renewMembership(payment, member);

                        System.out.println("Actualizado el miembro: " + member.getId() + "\nCreado el pago ID: " + payment.getId() + "\nCantidad: " + payment.getAmount());

                        inputIsValid = true;

                    } else {
                        System.out.println("El miembro no existe. Intente nuevamente. o presione --0-- para salir");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: El dato ingresado no es un numero valido. Intente nuevamente o presione --0-- para salir");
                }
            }

        } while (!inputIsValid);

    }
}