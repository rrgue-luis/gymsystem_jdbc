package dao.imp;

import dao.EmployeeDAO;
import dao.MySQLDBConnection;
import entities.Employee;

import java.sql.*;
import java.util.List;

public class EmployeeDAOImp implements MySQLDBConnection, EmployeeDAO {
    @Override
    public Employee insert(Employee entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO employee(name, surname, phone, address, hiring_date, salary, role, status, shift) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";


        try{
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence, Statement.RETURN_GENERATED_KEYS);

            SQLSentenceObject.setString(1, entity.getName());
            SQLSentenceObject.setString(2, entity.getSurname());
            SQLSentenceObject.setString(3, entity.getPhone());
            SQLSentenceObject.setString(4, entity.getAddress());
            SQLSentenceObject.setDate(5, java.sql.Date.valueOf(entity.getHiringDate()));
            SQLSentenceObject.setFloat(6, entity.getSalary());
            SQLSentenceObject.setString(7, entity.getEmployeeRole().name());
            SQLSentenceObject.setString(8, entity.getEmployeeStatus().name());
            SQLSentenceObject.setString(9, entity.getEmployeeShift().name());


            // ID | NOMBRE | DNI
            //RESULSET =
            // 1 | JORGE | 445512515

            int rowsInserted = SQLSentenceObject.executeUpdate();
            //int rowsInserted = 1; 3;2;  4; 0;
            //SQLSentenceObject.execute();

            /**
             * Acá llega si ya se creo en base de datos
             * lo que hace es obtener el ID con el que se creo en la base de datos
             * y setearselo al entity que voy a retornar, entonces
             * si se logro crear en base de datos (rowsInserted > 0) le voy a poder setear
             * a mi entity el id (setId) si no se creo en base de datos (rowsInserted < 1)
             * entonces mi entity va a quedar con el id 0 porque no se lo seteo ese quiere
             * decir que no se inserto.
             */
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = SQLSentenceObject.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
            SQLSentenceObject.close();
            return entity; // false = no se inserto || true = se inserto
        }catch(SQLException e){

            e.printStackTrace();
            return entity;
        }

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
