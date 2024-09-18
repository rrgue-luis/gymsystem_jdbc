package presentation;

import business.EmployeeService;
import business.impl.EmployeeServiceImp;
import entities.Employee;
import entities.Member;

import java.time.LocalDate;
import java.util.List;
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


        String input = null;
        Employee.EmployeeRole employeeRole = null;
        System.out.println("Ingrese el rol del empleado: ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE')");

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
        float salary = employeeService.calculateSalary(employeeRole);
        employee.setSalary(employeeService.calculateSalary(employeeRole));

        System.out.println("Sueldo asignado por defecto: " + salary);

        Employee.EmployeeShift employeeShift = null;
        System.out.println("Ingrese el turno del empleado: ('MORNING', 'AFTERNOON', 'NIGHT')");

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

        employee.setEmployeeStatus(Employee.EmployeeStatus.valueOf("ACTIVE"));

        employee = employeeService.insert(employee);

        if (employee.getId() > 0) {
            System.out.println("Se creo correctamente al empleado con ID: " + employee.getId());
        } else {
            System.out.println("NO se creo al empleado.");
        }

        return employee;
    }

    public List<Employee> obtainallMenu() {

        List<Employee> employeeList = employeeService.obtainAll();

        for(Employee employee : employeeList) {

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

        return employeeList;
    }

    /**Modifica manualmente el sueldo asignado por defecto, ideal para aumentos.
     * No recibe nada
     */
    public void updateSalary() {
        System.out.println("Ingrese el ID del empleado a modificar el sueldo: ");
        int input = scanner.nextInt();

        Employee searchedEmployee = employeeService.searchForID(input);

        System.out.println("Ingrese el nuevo sueldo para el empleado ID: " + input);
        float newSalary = scanner.nextFloat();

       employeeService.updateSalary(searchedEmployee, newSalary);

    }

    /**Muestra los datos del empleado, buscandolo por ID
     * Devuelve todos los datos
     */
    public void searchForIdMenu() {

        Employee searchedEmployee;

        System.out.println("Ingrese el ID que desea buscar: ");
        int input = scanner.nextInt();

        searchedEmployee = employeeService.searchForID(input);
        System.out.println(searchedEmployee);

    }

}
