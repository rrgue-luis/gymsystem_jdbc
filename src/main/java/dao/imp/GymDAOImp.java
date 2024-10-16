package dao.imp;

import dao.DAO;
import dao.GymDAO;
import dao.MySQLDBConnection;
import entities.Gym;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymDAOImp implements MySQLDBConnection, GymDAO {
    @Override
    public Gym insert(Gym entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO gym(name, address, schedule, phone, email, status) " + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence, Statement.RETURN_GENERATED_KEYS);

            SQLSentenceObject.setString(1, entity.getName());
            SQLSentenceObject.setString(2, entity.getAddress());
            SQLSentenceObject.setString(3, entity.getSchedule());
            //SQLSentenceObject.setString(3, entity.getSchedule().toString());
            SQLSentenceObject.setString(4, entity.getPhone());
            SQLSentenceObject.setString(5, entity.getEmail());
            SQLSentenceObject.setString(6, entity.getStatus().name());

            int rowsInserted = SQLSentenceObject.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = SQLSentenceObject.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
            SQLSentenceObject.close();
            return entity;

        } catch (SQLException e) {
            e.printStackTrace();
            return entity;
        }

    }

    @Override
    public void update(Gym entity) {

    }

    @Override
    public List<Gym> obtainAll() {

        List<Gym> gyms = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM gym";
        Statement ObjectSQLSentence = null;

        try {
            ObjectSQLSentence = connection.createStatement();
            ResultSet result = ObjectSQLSentence.executeQuery(SQLSentence);

            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String schedule = result.getString("schedule");
                String phone = result.getString("phone");
                String email = result.getString("email");

                Gym.Status status;
                String statusString = result.getString("status");
                try {
                    status = Gym.Status.valueOf(statusString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    // Valor predeterminado si el valor no coincide con el ENUM
                    status = Gym.Status.UNKNOWN;
                }


                Gym searchedGym = new Gym(id, name, address, schedule, phone, email, status);
                gyms.add(searchedGym);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ObjectSQLSentence.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return gyms;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public Gym searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

    @Override
    public boolean gymExists(Integer key) {
        return false;
    }
}
