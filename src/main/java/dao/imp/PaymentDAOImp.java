package dao.imp;

import dao.DAO;
import dao.MySQLDBConnection;
import dao.PaymentDAO;
import entities.Payment;

import java.sql.Connection;
import java.util.List;

public class PaymentDAOImp implements MySQLDBConnection, PaymentDAO {
    @Override
    public Payment insert(Payment entity) {


        return entity;
    }

    @Override
    public void update(Payment entity) {

    }

    @Override
    public List<Payment> obtainAll() {
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public Payment searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

    @Override
    public boolean paymentExists(Integer key) {
        return false;
    }
}
