package dao;

import entities.Employee;

import java.util.List;

public interface EmployeeDAO extends DAO<Employee, Integer>{

    float updateSalary(Employee entity, float newSalary);

    boolean employeeExists(Integer key);

    List<Employee> getEmployeesByGymId(int selectedGym);

}
