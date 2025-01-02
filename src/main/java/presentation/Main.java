package presentation;

import business.GymService;
import business.impl.GymServiceImp;

import java.util.InputMismatchException;
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
        boolean inputIsValid = false;

        System.out.print("Ingrese el ID del gym (o presione Enter para mantener el actual): ");
        do {
            try {
                inputString = scanner.nextLine().trim();
                if (inputString.equals("")) {
                    selectedGym = 2;
                    System.out.println("Gimnasio seleccionado: " + gymService.showName(selectedGym));
                    inputIsValid = true;
                } else {
                    selectedGym = Integer.parseInt(inputString);
                    gymExists = gymService.gymExists(selectedGym);
                    if (gymExists) {
                        System.out.println("Gimnasio seleccionado: " + gymService.showName(selectedGym));
                        inputIsValid = true;
                    } else {
                        System.out.println("El gimnasio seleccionado no existe. Intente nuevamente\nO presione enter para mantener el actual");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es válido. Intentelo de nuevo\nO presione enter para mantener el actual");
            }

        } while (!inputIsValid);

        input = -1;

        do {
            try {

                System.out.println("-----------SUCURSAL " + gymPresentation.showName(selectedGym) + "-----------");
                System.out.println("--1-- MIEMBROS");
                System.out.println("--2-- PAGOS");
                System.out.println("--3-- EMPLEADOS");
                System.out.println("--4-- SUCURSALES");
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
                                        memberPresentation.getAllMenu();
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
                                            paymentPresentation.getAllMenu();
                                            break;

                                        case 5:
                                            paymentPresentation.getMemberPayments();
                                            break;

                                        case 6:
                                            paymentPresentation.getGymPayments(selectedGym);
                                            break;

                                        case 7:
                                            paymentPresentation.getPaymentsByMethod();
                                            break;

                                        case 0:
                                            System.out.println("Saliendo menu pagos...");
                                            break;

                                        default:
                                            System.out.println("Saliendo...");
                                            break;
                                    }

                                } catch (InputMismatchException e) {
                                    System.out.println("Error. El dato ingresado no es válido. Intentelo nuevamente.");
                                }
                            } while (input != 0);
                            input = -1;
                            break;

                        case 3:
                            do {
                                System.out.println("--1-- AGREGAR EMPLEADO");
                                System.out.println("--2-- EDITAR EMPLEADO");
                                System.out.println("--3-- ELIMINAR EMPLEADO");
                                System.out.println("--4-- LISTAR *TODOS* LOS EMPLEADOS");
                                System.out.println("--5-- LISTAR EMPLEADOS *DE UN GYM*");
                                System.out.println("--6-- ACTUALIZAR SUELDO");
                                System.out.println("--PRESIONE 0 PARA SALIR--");

                                input = scanner.nextInt();

                                switch (input) {
                                    case 1:
                                        employeePresentation.insertMenu(selectedGym);
                                        break;

                                    case 2:
                                        employeePresentation.updateMenu(selectedGym);
                                        break;

                                    case 3:
                                        employeePresentation.deleteMenu();
                                        break;

                                    case 4:
                                        employeePresentation.getAllMenu();
                                        break;

                                    case 5:
                                        employeePresentation.getAllGymEmployees(selectedGym);
                                        break;

                                    case 6:
                                        employeePresentation.updateSalary();
                                        break;

                                    default:
                                        break;
                                }
                            } while (input != 0);
                            input = -1;
                            break;
                        case 4:
                            do {
                                System.out.println("--1-- AGREGAR GYM");
                                System.out.println("--2-- EDITAR GYM");
                                System.out.println("--3-- ELIMINAR GYM");
                                System.out.println("--4-- LISTAR *TODOS* LOS GYM");
                                System.out.println("--PRESIONE 0 PARA SALIR--");

                                input = scanner.nextInt();

                                switch (input) {
                                    case 1:
                                        gymPresentation.insertMenu();
                                        break;

                                    case 2:
                                        gymPresentation.updateMenu();
                                        break;

                                    case 3:
                                        gymPresentation.deleteMenu();
                                        break;

                                    case 4:
                                        gymPresentation.obtainAllMenu();
                                        break;
                                }

                            } while (input != 0);
                            input = -1;
                            break;

                        default:
                            System.out.println("Saliendo...");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error, el dato ingresado no es válido. Intentelo nuevamente.!!!!!!!");
                    scanner.nextLine();
                }

            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es válido. Intente nuevamente.");
                scanner.nextLine();
            }
        } while (input != 0);

        scanner.close();

    }

}