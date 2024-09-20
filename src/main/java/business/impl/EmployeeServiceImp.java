package business.impl;

import business.EmployeeService;
import dao.EmployeeDAO;
import dao.MemberDAO;
import dao.imp.EmployeeDAOImp;
import dao.imp.MemberDAOImp;
import entities.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImp implements EmployeeService {

    Scanner scanner = new Scanner(System.in);

    private final EmployeeDAOImp employeeDAOImp;

    EmployeeDAO employeeDAO = new EmployeeDAOImp();


    @Override
    public Employee insert(Employee employee) {

        employee = employeeDAO.insert(employee);
        return employee;
    }

    @Override
    public void delete(Integer key) {
        employeeDAO.delete(key);
    }

    @Override
    public Employee searchForId(Integer key) {

        EmployeeDAO employeeDAO = new EmployeeDAOImp();
        return employeeDAO.searchForId(key);

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    public float calculateSalary (Employee employee, Employee.EmployeeRole employeeRole) {

        String employeeRoleString = employee.getEmployeeRole().name();

        float finalSalary;

        if(employeeRoleString.equals("BOSS")) {
             finalSalary = 9000.32f;
        } else if (employeeRoleString.equals("MANAGER")) {
            finalSalary = 5300.93f;
        } else if (employeeRoleString.equals("TRAINER")) {
            finalSalary = 1500.32f;
        } else {
            finalSalary = 1200.33f;
        }

        return finalSalary;
    }

    @Override
    public float updateSalary(Employee employee, float newSalary) {

        employeeDAO.updateSalary(employee, newSalary);

        return 0;
    }




    @Override
    public boolean employeeExists(Integer key) {

        boolean employeeExists = employeeDAO.employeeExists(key);

        if (employeeExists) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Employee> obtainAll() {

        List<Employee> employees = new ArrayList<>();
        employees = employeeDAO.obtainAll();

        return employees;
    }

    @Override
    public boolean isEmployeeActive(int employeeId) {
        return false;
    }

    @Override
    public LocalDate parsedDate(String parsingDate) {
        LocalDate parsedDate = null;

        while (parsedDate == null) {

            if (parsingDate == null || parsingDate.isEmpty()) {
                System.out.println("Por favor, ingrese una fecha en el formato correcto: 'AAAA-MM-DD'");
                parsingDate = scanner.nextLine();
            }

            try {
                parsedDate = LocalDate.parse(parsingDate);
                System.out.println("Fecha OK");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR de sintaxis al ingresar fecha, recuerde que el formato es: 'AAAA-MM-DD'");
                parsingDate = null;
            }
        }

        return parsedDate;
    }

    public EmployeeServiceImp() {
        this.employeeDAOImp = new EmployeeDAOImp();
    }

    public EmployeeServiceImp(EmployeeDAOImp employeeDAOImp) {
        this.employeeDAOImp = employeeDAOImp;
    }

}
