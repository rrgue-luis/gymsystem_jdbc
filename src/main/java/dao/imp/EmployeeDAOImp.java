package dao.imp;

import dao.DAO;
import dao.MySQLDBConnection;
import entities.Employee;

import java.sql.Connection;
import java.util.List;

public class EmployeeDAOImp implements MySQLDBConnection, DAO<Employee, Integer> {
    @Override
    public Employee insert(Employee entity) {
        return entity;
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public List<Employee> obtainAll() {
        return null;
    }

    @Override
    public void delete(Integer key) {

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
