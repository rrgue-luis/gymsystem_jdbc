package business.impl;

import business.EmployeeService;
import dao.EmployeeDAO;
import dao.imp.EmployeeDAOImp;
import entities.Employee;

import java.time.LocalDate;
import java.util.List;

public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeDAOImp employeeDAOImp;

    EmployeeDAO employeeDAO = new EmployeeDAOImp();


    @Override
    public Employee insert(Employee employee) {

        employee = employeeDAO.insert(employee);
        return employee;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public Employee searchForID(Integer key) {

        EmployeeDAO employeeDAO = new EmployeeDAOImp();
        Employee searchedEmployee = employeeDAO.searchForId(key);
        return searchedEmployee;

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public boolean employeeExists(Integer key) {
        return false;
    }

    @Override
    public List<Employee> obtainAll() {
        return null;
    }

    @Override
    public boolean isEmployeeActive(int employeeId) {
        return false;
    }

    @Override
    public LocalDate parsedDate(String parsingDate) {
        return null;
    }

    public EmployeeServiceImp() {
        this.employeeDAOImp = new EmployeeDAOImp();
    }

    public EmployeeServiceImp(EmployeeDAOImp employeeDAOImp) {
        this.employeeDAOImp = employeeDAOImp;
    }

}
