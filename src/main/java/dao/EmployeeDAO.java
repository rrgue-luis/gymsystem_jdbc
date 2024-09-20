package dao;

import entities.Employee;

public interface EmployeeDAO extends DAO<Employee, Integer>{

    float updateSalary(Employee entity, float newSalary);

    boolean employeeExists(Integer key);

}
