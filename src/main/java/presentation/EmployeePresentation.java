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

        LocalDate parsedDate = employeeService.parseDate(parsingDate);
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
        float salary = employeeService.calculateSalary(employee, employeeRole);
        employee.setSalary(employeeService.calculateSalary(employee, employeeRole));

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

        //asignacion a activo por defecto
        employee.setEmployeeStatus(Employee.EmployeeStatus.valueOf("ACTIVE"));

        employee = employeeService.insert(employee);

        if (employee.getId() > 0) {
            System.out.println("Se creo correctamente al empleado con ID: " + employee.getId());
        } else {
            System.out.println("NO se creo al empleado.");
        }

        return employee;
    }

    public void updateMenu(){

        System.out.println("Ingrese el ID del empleado a actualizar:");
        obtainAllMenu();
        int input = scanner.nextInt();

        boolean employeeExists = employeeService.employeeExists(input);

        if(employeeExists) {
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

            System.out.println("Ingrese la fecha de contrato del empleado");
            String parsingDate = scanner.nextLine();

            LocalDate parsedDate = employeeService.parseDate(parsingDate);

            employee.setHiringDate(parsedDate);

            System.out.println("Ingrese el rol del empleado: ");
            String inputString = scanner.nextLine().toUpperCase();

            Employee.EmployeeRole employeeRole = null;

            try {
                employeeRole = Employee.EmployeeRole.valueOf(inputString);
                employee.setEmployeeRole(employeeRole);
                System.out.println("Rol elegido: " + employeeRole);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de ROL no valido. Intente nuevamente ('TRAINER', 'EMPLOYEE', 'MANAGER', 'BOSS')");
                e.printStackTrace();
            }

            System.out.println("Ingrese el turno del empleado: ");
            inputString = scanner.nextLine().toUpperCase();

            Employee.EmployeeShift employeeShift = null;

            try {
                employeeShift = Employee.EmployeeShift.valueOf(inputString);
                employee.setEmployeeShift(employeeShift);
                System.out.println("Turno elegido: " + employeeShift);
            } catch (IllegalArgumentException e) {
                System.out.println("Turno no valido. Intente nuevamente ('MORNING', 'AFTERNOON', 'NIGHT')");
                e.printStackTrace();
            }

            employee.setEmployeeStatus(Employee.EmployeeStatus.ACTIVE);

            employeeService.updateEmployee(employee);
        } else {
            System.out.println("El empleado no existe.");
        }


    }

    public void deleteMenu() {

        boolean employeeExists;
        do {
            System.out.println("Ingrese el id del miembro a eliminar: ");
            obtainAllMenu();
            System.out.println("-----------LISTA-----------");
            int input = scanner.nextInt();

            employeeExists = employeeService.employeeExists(input);
            if (employeeService.employeeExists(input)) {
                System.out.println("Empleado eliminado: " + input);
                employeeService.delete(input);
            } else {
                System.out.println("No existe empleado con ese ID, intente nuevamente");
            }
        } while (!employeeExists);

    }

    public void obtainAllMenu() {

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

    }

    /**Modifica manualmente el sueldo asignado por defecto, ideal para aumentos.
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

    /**Muestra los datos del empleado, buscandolo por ID
     * Devuelve todos los datos
     */
    public void searchForIdMenu() {

        Employee searchedEmployee;
        int input;
        do {

            System.out.println("Ingrese el ID que desea buscar: ");
            input = scanner.nextInt();

            searchedEmployee = employeeService.searchForId(input);

            if(searchedEmployee!= null) {
                System.out.println(searchedEmployee);
            } else {
                System.out.println("No existe ese miembro, intente con otro ID. \n Volver al menú: --(0)--");
            }

            if(input == 0) {
                System.out.println("Volviendo al menú");
                break;
            }

        } while(searchedEmployee == null);

    }

}
