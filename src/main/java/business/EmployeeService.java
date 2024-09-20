package business;

import dao.EmployeeDAO;
import entities.Employee;
import entities.Member;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee insert(Employee employee);

    public void delete(Integer key);

    Employee searchForId(Integer key);

    void updateEmployee(Employee employee);

    public boolean employeeExists(Integer key);

    List<Employee> obtainAll();

    public boolean isEmployeeActive(int employeeId);

    LocalDate parseDate(String parsingDate);

    public float calculateSalary(Employee employee, Employee.EmployeeRole employeeRole);

    public float updateSalary(Employee entity, float newSalary);

}
