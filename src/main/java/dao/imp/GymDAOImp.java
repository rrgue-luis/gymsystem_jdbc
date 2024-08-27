package dao.imp;

import dao.DAO;
import dao.MySQLDBConnection;
import entities.Gym;

import java.sql.Connection;
import java.util.List;

public class GymDAOImp implements MySQLDBConnection, DAO<Gym, Integer> {
    @Override
    public boolean insert(Gym entity) {
        return false;
    }

    @Override
    public void update(Gym entity) {

    }

    @Override
    public List<Gym> obtainAll() {
        return null;
    }

    @Override
    public void delete(Gym entity) {

    }

    @Override
    public Gym searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }
}
