package presentation;

import business.EmployeeService;
import business.GymService;
import business.impl.EmployeeServiceImp;
import business.impl.GymServiceImp;
import entities.Employee;
import enums.employee.EmployeeRole;
import enums.employee.EmployeeShift;
import enums.employee.EmployeeStatus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeePresentation {
    Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImp();
    GymService gymService = new GymServiceImp();


    public void insertMenu(int selectedGym) {

        Employee employee = new Employee();

        selectedGym = setSelectedGym(selectedGym);

        employee.setGymId(selectedGym);

        System.out.println("Ingrese el nombre del empleado: ");
        employee.setName(scanner.nextLine());

        System.out.println("Ingrese el apellido del empleado: ");
        employee.setSurname(scanner.nextLine());

        System.out.println("Ingrese el telefono del empleado: ");
        employee.setPhone(scanner.nextLine());

        System.out.println("Ingrese la direccion del empleado: ");
        employee.setAddress(scanner.nextLine());

        do {
            System.out.println("Ingrese la fecha de contratacion del empleado: (ENTER: FECHA ACTUAL)");
            String parsingDate = scanner.nextLine();

            if (parsingDate.equals("")) {
                System.out.println("Fecha de contratacion: Hoy " + LocalDate.now());
                LocalDate parsedDate = LocalDate.now();
                employee.setHiringDate(parsedDate);
                break;
            } else {
                try {
                    LocalDate parsedDate = LocalDate.parse(parsingDate);
                    employee.setHiringDate(parsedDate);
                    System.out.println("Fecha asignada: " + parsedDate);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Error de syntaxis, recuerde que el formato es: 'AAAA-MM-DD' (ENTER: FECHA ACTUAL)");
                }
            }
        } while (true);


        String input = null;
        EmployeeRole employeeRole = null;
        System.out.println("Ingrese el rol del empleado: ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE')");

        while (employeeRole == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                employeeRole = EmployeeRole.valueOf(input);
                System.out.println("Rol elegido: " + employeeRole);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de ROL no valido. Intente nuevamente ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE'):");
            }
        }


        employee.setEmployeeRole(employeeRole);
        float salary = employeeService.calculateSalary(employee, employeeRole);
        employee.setSalary(employeeService.calculateSalary(employee, employeeRole));

        System.out.println("Sueldo asignado por defecto: " + salary);

        EmployeeShift employeeShift = null;
        System.out.println("Ingrese el turno del empleado: ('MORNING', 'AFTERNOON', 'NIGHT')");

        while (employeeShift == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                employeeShift = EmployeeShift.valueOf(input);
                System.out.println("Turno elegido: " + employeeShift);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de turno no valido. Intente nuevamente ('MORNING', 'AFTERNOON', 'NIGHT'):");
            }

        }

        employee.setEmployeeShift(employeeShift);

        //asignacion a activo por defecto
        employee.setEmployeeStatus(EmployeeStatus.valueOf("ACTIVE"));

        employee = employeeService.insert(employee);

        if (employee.getId() > 0) {
            System.out.println("Se creo correctamente al empleado con ID: " + employee.getId());
        } else {
            System.out.println("NO se creo al empleado.");
        }

    }

    public void updateMenu() {

        System.out.println("Ingrese el ID del empleado a actualizar:");
        obtainAllMenu();
        boolean inputIsValid = false;
        boolean employeeExists = false;
        int input = -1;
        do {
            try {
                do {
                input = scanner.nextInt();
                inputIsValid = true;

                employeeExists = employeeService.employeeExists(input);
                if (employeeExists) {
                    Employee employee = new Employee();

                    employee.setId(input);

                    System.out.println("Ingrese el nombre del empleado: ");
                    scanner.nextLine();
                    employee.setName(scanner.nextLine());

                    System.out.println("Ingrese el apellido del empleado: ");
                    employee.setSurname(scanner.nextLine());

                    System.out.println("Ingrese el telefono del empleado: ");
                    employee.setPhone(scanner.nextLine());

                    System.out.println("Ingrese la direccion del empleado");
                    employee.setAddress(scanner.nextLine());

                    do {
                        System.out.println("Ingrese la fecha de contratación: (ENTER: FECHA ACTUAL)");
                        String parsingDate = scanner.nextLine();

                        if (parsingDate.equals("")) {
                            System.out.println("Fecha de contratación: Hoy " + LocalDate.now());
                            LocalDate parsedDate = LocalDate.now();
                            employee.setHiringDate(parsedDate);
                            break;
                        } else {
                            try {
                                LocalDate parsedDate = LocalDate.parse(parsingDate);
                                employee.setHiringDate(parsedDate);
                                System.out.println("Fecha asignada: " + parsedDate);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Error de syntaxis, recuerde que el formato es: 'AAAA-MM-DD'");
                            }
                        }
                    } while (true);

                    System.out.println("Ingrese el rol del empleado: ('TRAINER', 'EMPLOYEE', 'MANAGER', 'BOSS')");

                    String inputString;
                    EmployeeRole employeeRole = null;
                    while(employeeRole == null) {

                        inputString = scanner.nextLine().toUpperCase();

                        try {
                            employeeRole = EmployeeRole.valueOf(inputString);
                            EmployeeRole.valueOf(employeeRole.toString());
                            System.out.println("Rol elegido: " + employeeRole);
                            employee.setEmployeeRole(employeeRole);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo de ROL no valido. Intente nuevamente ('TRAINER', 'EMPLOYEE', 'MANAGER', 'BOSS')");
                        }
                    }

                    employee.setSalary(employeeService.calculateSalary(employee, employeeRole));
                    System.out.println("Sueldo asignado por defecto: " + employee.getSalary());

                    System.out.println("Ingrese el turno del empleado: ('MORNING', 'AFTERNOON', 'NIGHT')");

                    EmployeeShift employeeShift = null;
                    while (employeeShift == null) {

                        inputString = scanner.nextLine().toUpperCase();
                        try {
                            employeeShift = EmployeeShift.valueOf(inputString);
                            employee.setEmployeeShift(employeeShift);
                            System.out.println("Turno elegido: " + employeeShift);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Turno no valido. Intente nuevamente ('MORNING', 'AFTERNOON', 'NIGHT')");
                        }
                    }


                    employee.setEmployeeStatus(EmployeeStatus.ACTIVE);

                    employeeService.updateEmployee(employee, employeeRole);
                } else {
                    System.out.println("El miembro seleccionado no existe.");
                    employeeExists = false;
                }
            }while(!employeeExists);

            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es un ID válido");
                scanner.nextLine();
            }
        } while(!inputIsValid);






}

    public void deleteMenu() {

        boolean employeeExists;
        boolean inputIsValid = false;
        int input = -1;
        do {
            System.out.println("Ingrese el id del empleado a eliminar: --0-- para salir");
            obtainAllMenu();
            System.out.println("-----------LISTA (PRESIONE 0 PARA SALIR)-----------");
            try {
                do {
                    input = scanner.nextInt();
                    if(input == 0) {
                        System.out.println("Saliendo...");
                        return;
                    }
                    employeeExists = employeeService.employeeExists(input);
                    if(!employeeExists) {
                        System.out.println("El empleado seleccionado no existe. Intente nuevamente. O presione --0-- para salir");
                    } else {
                        employeeService.delete(input);
                        System.out.println("Desea seguir eliminando empleados? --0-- para SALIR, --Cualquier tecla-- SEGUIR ELIMINANDO");
                        try {
                            input = scanner.nextInt();
                            if(input == 0){
                                return;
                            }
                        } catch(InputMismatchException e) {
                            System.out.println("Saliendo...");
                        }

                    }
                } while(!employeeExists);

            } catch(InputMismatchException e) {
                System.out.println("El dato ingresado no es válido. Intente nuevamente o --0-- para salir");
                scanner.nextLine();
            }

        } while (true);

    }

    public void obtainAllMenu() {

        List<Employee> employeeList = employeeService.obtainAll();

        for (Employee employee : employeeList) {

            System.out.println("---------------");
            System.out.println("ID: " + employee.getId());
            System.out.println("Nombre: " + employee.getName());
            System.out.println("Apellido: " + employee.getSurname());
            System.out.println("Telefono: " + employee.getPhone());
            System.out.println("Direccion: " + employee.getAddress());
            System.out.println("Fecha de contratación: " + employee.getHiringDate());
            System.out.println("Estado: " + employee.getEmployeeStatus());
            System.out.println("Rol: " + employee.getEmployeeRole());
            System.out.println("Turno: " + employee.getEmployeeShift());
            System.out.println("Salario: " + employee.getSalary());
        }

    }

    public void getAllGymEmployees(int selectedGym) {

    }

    public int setSelectedGym(int selectedGym) {
        System.out.print("Ingrese el ID del gym (o presione Enter para mantener el actual): ");
        String inputString;
        boolean inputIsValid = false;
        do {
            inputString = scanner.nextLine().trim();

            if (inputString.equals("")) {
                if (!gymService.gymExists(selectedGym)) {
                    System.out.println("ERROR: El gimnasio no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                } else {
                    System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                    inputIsValid = true;
                }
            } else {
                try {
                    int gymId = Integer.parseInt(inputString);
                    if (!gymService.gymExists(gymId)) {
                        System.out.println("EL gimnasio ingresado no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                    } else {
                        selectedGym = gymId;
                        System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                        inputIsValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: EL dato ingresado no es un numero valido. Intente nuevamente con un numero o ENTER para mantener el actual");
                }
            }
        } while (!inputIsValid);

        return selectedGym;
    }

    /**
     * Modifica manualmente el sueldo asignado por defecto, ideal para aumentos.
     * No recibe nada
     */
    public void updateSalary() {
        System.out.println("Ingrese el ID del empleado a modificar el sueldo: ");
        int input = scanner.nextInt();

        Employee searchedEmployee = employeeService.searchForId(input);

        System.out.println("Ingrese el nuevo sueldo para el empleado ID: " + input);
        float newSalary = scanner.nextFloat();

        employeeService.updateSalary(searchedEmployee, newSalary);

    }

    /**
     * Muestra los datos del empleado, buscandolo por ID
     * Devuelve todos los datos
     */
    public void searchForIdMenu() {

        Employee searchedEmployee;
        int input;
        do {

            System.out.println("Ingrese el ID que desea buscar: ");
            input = scanner.nextInt();

            searchedEmployee = employeeService.searchForId(input);

            if (searchedEmployee != null) {
                System.out.println(searchedEmployee);
            } else {
                System.out.println("No existe ese miembro, intente con otro ID. \n Volver al menú: --(0)--");
            }

            if (input == 0) {
                System.out.println("Volviendo al menú");
                break;
            }

        } while (searchedEmployee == null);

    }

}
