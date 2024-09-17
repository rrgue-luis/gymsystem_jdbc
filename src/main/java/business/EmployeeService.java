package business;

import dao.EmployeeDAO;
import entities.Employee;
import entities.Member;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee insert(Employee employee);

    public void delete(Integer key);

    Employee searchForID(Integer key);

    void updateEmployee(Employee employee);

    public boolean employeeExists(Integer key);

    List<Employee> obtainAll();

    public boolean isEmployeeActive(int employeeId);

    LocalDate parsedDate(String parsingDate);

}
