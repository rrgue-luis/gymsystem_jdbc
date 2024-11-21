package business;

import entities.Employee;
import enums.employee.EmployeeRole;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee insert(Employee employee);

    void delete(Integer key);

    Employee searchForId(Integer key);

    void updateEmployee(Employee employee, EmployeeRole employeeRole);

    boolean employeeExists(Integer key);

    List<Employee> obtainAll();

    boolean isEmployeeActive(int employeeId);

    LocalDate parseDate(String parsingDate);

    float calculateSalary(Employee employee, EmployeeRole employeeRole);

    void updateSalary(Employee entity, float newSalary);

}
