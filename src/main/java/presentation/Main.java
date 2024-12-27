package presentation;

import business.EmployeeService;
import business.GymService;
import business.MemberService;
import business.impl.EmployeeServiceImp;
import business.impl.GymServiceImp;
import business.impl.MemberServiceImp;
import entities.Member;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MemberPresentation memberPresentation = new MemberPresentation();
        EmployeePresentation employeePresentation = new EmployeePresentation();
        GymPresentation gymPresentation = new GymPresentation();
        PaymentPresentation paymentPresentation = new PaymentPresentation();
        MembershipPresentation membershipPresentation = new MembershipPresentation();
        GymService gymService = new GymServiceImp();

        int input = 0;
        int selectedGym = 0;


        System.out.println("-----GYM SYSTEM-----");

        System.out.println("SELECCIONE LA SUCURSAL A MANEJAR: ");
        String inputString;
        boolean gymExists;
        System.out.print("Ingrese el ID del gym (o presione Enter para mantener el actual): ");
        do {
            inputString = scanner.nextLine().trim();

            if (!inputString.equals("")) {

                try {
                    selectedGym = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El valor ingresado no es un numero valido");
                    System.out.println("Ingrese el ID del gym (o presione Enter para mantener el actual):");
                }
            } else {
                selectedGym = 2;
                System.out.println("GYM Seleccionado: ACTUAL " + gymPresentation.showName(selectedGym));
            }

            gymExists = gymService.gymExists(selectedGym);
            System.out.println(gymExists ? "" : "El gym ingresado no existe, intentelo nuevamente.");

        } while (!gymExists);


        do {
            System.out.println("-----------SUCURSAL " + gymPresentation.showName(selectedGym) + "-----------");
            System.out.println("--1-- MIEMBROS");
            System.out.println("--2-- PAGOS");
            System.out.println("--3-- SUCURSALES");
            System.out.println("--4-- EMPLEADOS");
            System.out.println("**PRESIONE 0 PARA SALIR DEL SISTEMA**");



            try {
                input = scanner.nextInt();
                switch (input) {

                    case 1:
                        do {
                            System.out.println("--1-- AGREGAR MIEMBRO");
                            System.out.println("--2-- EDITAR MIEMBRO");
                            System.out.println("--3-- ELIMINAR MIEMBRO");
                            System.out.println("--4-- RENOVAR MEMBRESÍA");
                            System.out.println("--5-- MOSTRAR *TODOS* LOS MIEMBROS");
                            System.out.println("--6-- LISTAR MIEMBROS *DE UN GYM*");
                            System.out.println("--PRESIONE 0 PARA SALIR--");

                            input = scanner.nextInt();

                            switch (input) {
                                case 1:
                                    memberPresentation.insertMenu(selectedGym);
                                    break;

                                case 2:
                                    memberPresentation.updateMenu(selectedGym);
                                    break;

                                case 3:
                                    memberPresentation.deleteMenu();
                                    break;

                                case 4:
                                    membershipPresentation.renewMembership(selectedGym);
                                    break;

                                case 5:
                                    memberPresentation.obtainAllMenu();
                                    break;

                                case 6:
                                    gymPresentation.listGymEmployeesMenu(selectedGym);

                                default:
                                    break;
                            }
                        } while (input != 0);
                        input = -1;
                        break;

                    case 2:
                        do {

                            System.out.println("\n--MENU PAGOS--");
                            System.out.println("--1-- AGREGAR PAGO MANUALMENTE");
                            System.out.println("--2-- ELIMINAR PAGO MANUALMENTE");
                            System.out.println("--3-- EDITAR PAGO MANUALMENTE");
                            System.out.println("--------VER PAGOS--------");
                            System.out.println("\n--4-- TODOS LOS PAGOS");
                            System.out.println("--5-- DE UN MIEMBRO");
                            System.out.println("--6-- EN UN GYM");
                            System.out.println("--7-- POR METODO DE PAGO");
                            System.out.println("--PRESIONE 0 PARA SALIR--");

                            input = scanner.nextInt();
                            try {


                                switch (input) {
                                    case 1:
                                        paymentPresentation.insertMenu(selectedGym);
                                        break;

                                    case 2:
                                        paymentPresentation.deleteMenu();
                                        break;

                                    case 3:
                                        paymentPresentation.updateMenu(selectedGym);
                                        break;

                                    case 4:
                                        paymentPresentation.obtainAllMenu();
                                        break;

                                    case 5:
                                        paymentPresentation.listMemberPayments();
                                        break;

                                    case 6:
                                        paymentPresentation.listGymPayments(selectedGym);
                                        break;

                                    case 7:
                                        paymentPresentation.listPaymentsByMethod();
                                        break;

                                    case 0:
                                        System.out.println("Saliendo menu pagos...");
                                        break;

                                    default:
                                        System.out.println("Opcion no valida.");
                                        break;
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("Error. El dato ingresado no es válido. Intentelo nuevamente.");
                            }
                        } while (input != 0);
                        input = -1;
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Error, el dato ingresado no es válido. Intentelo nuevamente.");
                scanner.nextLine();
            }

        } while (input != 0);

        scanner.close();

    }

}