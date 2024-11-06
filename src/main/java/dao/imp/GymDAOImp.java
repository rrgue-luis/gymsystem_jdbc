package dao.imp;

import dao.DAO;
import dao.GymDAO;
import dao.MySQLDBConnection;
import entities.Employee;
import entities.Gym;
import entities.GymEmployeesDTO;

import javax.xml.transform.Result;
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
        Connection connection = getConnection();
        String checkId = "SELECT COUNT(*) FROM gym WHERE id=?";
        String SQLSentenceUpdate = "UPDATE gym SET name=?, address=?, schedule=?, phone=?, email=?, status=? WHERE id=?";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkId);
            checkStmt.setInt(1, entity.getId());
            ResultSet result = checkStmt.executeQuery();

            result.next();
            int count = result.getInt(1);

            if (count > 0) {

                PreparedStatement updateStmt = connection.prepareStatement(SQLSentenceUpdate);
                updateStmt.setString(1, entity.getName());
                updateStmt.setString(2, entity.getAddress());
                updateStmt.setString(3, entity.getSchedule());
                updateStmt.setString(4, entity.getPhone());
                updateStmt.setString(5, entity.getEmail());
                updateStmt.setString(6, entity.getStatus().name());
                updateStmt.setInt(7, entity.getId());

                updateStmt.executeUpdate();
                updateStmt.close();
                System.out.println("Actualizacion realizada con éxito.");

            } else {
                System.out.println("No existe gym con ese ID: " + entity.getId());
            }

            result.close();
            checkStmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        Connection connection = getConnection();

        String SQLSentence = "DELETE FROM gym WHERE id = ?";

        try {
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence);
            SQLSentenceObject.setInt(1, key);
            SQLSentenceObject.executeUpdate();
            SQLSentenceObject.close();
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Gym searchForId(Integer key) {

        Gym searchedGym = null;

        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM gym WHERE ID = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQLSentence);
            preparedStatement.setInt(1, key);
            ResultSet result = preparedStatement.executeQuery();

            if(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String address = result.getString("address");
                String schedule = result.getString("schedule");
                String phone = result.getString("phone");
                String email = result.getString("email");

                String statusString = result.getString("status");
                Gym.Status status = Gym.Status.valueOf(statusString.toUpperCase());

                searchedGym = new Gym(id, name, address, phone, email, schedule, status);

            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {

        } try {
            if(preparedStatement != null) {
                preparedStatement.close();
            } if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchedGym;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

    @Override
    public boolean gymExists(Integer key) {
        Connection connection = getConnection();
        String checkSQL = "SELECT COUNT(*) FROM gym WHERE id=?";

        try {
            PreparedStatement checkSQLStatement = connection.prepareStatement(checkSQL);
            checkSQLStatement.setInt(1, key);
            ResultSet result = checkSQLStatement.executeQuery();

            result.next();
            int count = result.getInt(1);

            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<GymEmployeesDTO> obtainGymEmployees(Integer key) {
        List<GymEmployeesDTO> gymEmployeesList = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT e.id AS employee_id, e.name AS employee_name, g.id AS gym_id, g.name AS gym_name\n" +
                "FROM employee e\n" +
                "INNER JOIN gym_employees ge ON e.id = ge.employee_id\n" +
                "INNER JOIN gym g ON g.id = ge.gym_id\n" +
                "WHERE g.id = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQLSentence);
            preparedStatement.setInt(1, key);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {

                int employeeId = result.getInt("employee_id");
                String employeeName = result.getString("employee_name");
                int gymId = result.getInt("gym_id");
                String gymName = result.getString("gym_name");

                GymEmployeesDTO gymEmployees = new GymEmployeesDTO(gymId, employeeId, gymName, employeeName);

                gymEmployeesList.add(gymEmployees);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return gymEmployeesList;
    }

}
