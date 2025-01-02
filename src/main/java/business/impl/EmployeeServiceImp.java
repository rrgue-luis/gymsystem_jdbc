package business.impl;

import business.EmployeeService;
import dao.EmployeeDAO;
import dao.imp.EmployeeDAOImp;
import entities.Employee;
import enums.employee.EmployeeRole;

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
    public void updateEmployee(Employee employee, EmployeeRole employeeRole) {
        employeeDAO.update(employee);
    }

    public float calculateSalary (Employee employee, EmployeeRole employeeRole) {

        String employeeRoleString = employeeRole.name();

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
    public void updateSalary(Employee employee, float newSalary) {

        employeeDAO.updateSalary(employee, newSalary);

    }

    @Override
    public boolean isValidSalary(float salary) {
        if(salary <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Employee> getEmployeesByGymId(int selectedGym) {
        return employeeDAO.getEmployeesByGymId(selectedGym);
    }

    @Override
    public boolean employeeExists(Integer key) {
        return employeeDAO.employeeExists(key);
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
    public LocalDate parseDate(String parsingDate) {
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
