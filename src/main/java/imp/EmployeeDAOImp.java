package imp;

import dao.DAO;
import dao.MySQLDBConnection;
import entities.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDAOImp implements MySQLDBConnection, DAO<Employee, Integer> {
    @Override
    public boolean insert(Employee entity) {
        return false;
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public List<Employee> obtainAll() {
        return null;
    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public Employee searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }
}
