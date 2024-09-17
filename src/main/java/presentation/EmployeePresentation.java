package presentation;

import business.EmployeeService;
import business.impl.EmployeeServiceImp;
import entities.Employee;
import entities.Member;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeePresentation {
    Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService =  new EmployeeServiceImp();


    public Employee insertMenu() {

        Employee employee = new Employee();

        System.out.println("Ingrese el nombre del empleado: ");
        employee.setName(scanner.nextLine());

        System.out.println("Ingrese el apellido del empleado: ");
        employee.setSurname(scanner.nextLine());

        System.out.println("Ingrese el telefono del empleado: ");
        employee.setPhone(scanner.nextLine());

        System.out.println("Ingrese la direccion del empleado: ");
        employee.setAddress(scanner.nextLine());

        System.out.println("Ingrese la fecha de contratacion del empleado: ");
        String parsingDate = scanner.nextLine();

        LocalDate parsedDate = employeeService.parsedDate(parsingDate);
        employee.setHiringDate(parsedDate);

        System.out.println("Ingrese el sueldo del empleado:");
        employee.setSalary(scanner.nextFloat());


        String input = null;
        Employee.EmployeeRole employeeRole = null;
        System.out.println("Ingrese el rol del empleado: ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE')");
        scanner.nextLine();

        while(employeeRole == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                employeeRole = Employee.EmployeeRole.valueOf(input);
                System.out.println("Rol elegido: " + employeeRole);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de ROL no valido. Intente nuevamente ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE'):");
            }

        }

        employee.setEmployeeRole(employeeRole);


        Employee.EmployeeShift employeeShift = null;
        System.out.println("Ingrese el turno del empleado: ('MORNING', 'AFTERNOON', 'NIGHT')");
        scanner.nextLine();

        while(employeeShift == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                employeeShift = Employee.EmployeeShift.valueOf(input);
                System.out.println("Turno elegido: " + employeeShift);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de turno no valido. Intente nuevamente ('MORNING', 'AFTERNOON', 'NIGHT'):");
            }

        }

        employee.setEmployeeShift(employeeShift);




        return employee;
    }

}
