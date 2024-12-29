package dao.imp;

import dao.EmployeeDAO;
import dao.MySQLDBConnection;
import entities.Employee;
import enums.employee.EmployeeShift;
import enums.employee.EmployeeRole;
import enums.employee.EmployeeStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements MySQLDBConnection, EmployeeDAO {
    @Override
    public Employee insert(Employee entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO employee(name, surname, phone, address, hiring_date, salary, role, status, shift, gym_id) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {

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
            SQLSentenceObject.setInt(10, entity.getGymId());


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
        } catch (SQLException e) {

            e.printStackTrace();
            return entity;
        }

    }

    @Override
    public void update(Employee entity) {

        Connection connection = getConnection();
        String checkId = "SELECT COUNT(*) FROM employee WHERE id=?";
        String SQLSentenceUpdate = "UPDATE employee SET name=?, surname=?, hiring_date=?, salary=?, role=?, status=?, shift=?, phone=?, address=? WHERE id=?";

        try {

            PreparedStatement checkStmt = connection.prepareStatement(checkId);
            checkStmt.setInt(1, entity.getId());
            ResultSet resultSet = checkStmt.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {

                PreparedStatement updateStmt = connection.prepareStatement(SQLSentenceUpdate);
                updateStmt.setString(1, entity.getName());
                updateStmt.setString(2, entity.getSurname());
                updateStmt.setDate(3, java.sql.Date.valueOf(entity.getHiringDate()));
                updateStmt.setFloat(4, entity.getSalary());
                updateStmt.setString(5, entity.getEmployeeRole().name());
                updateStmt.setString(6, entity.getEmployeeStatus().name());
                updateStmt.setString(7, entity.getEmployeeShift().name());
                updateStmt.setString(8, entity.getPhone());
                updateStmt.setString(9, entity.getAddress());
                updateStmt.setInt(10, entity.getId());

                updateStmt.executeUpdate();
                updateStmt.close();
                System.out.println("Actualización realizada con éxito.");
            } else {
                System.out.println("No existe empleado con ese ID: " + entity.getId());
            }

            resultSet.close();
            checkStmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> obtainAll() {

        List<Employee> employees = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM employee";
        Statement ObjectSQLSentence = null;

        try {
            ObjectSQLSentence = connection.createStatement();
            ResultSet result = ObjectSQLSentence.executeQuery(SQLSentence);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String phone = result.getString("phone");
                String address = result.getString("address");

                String employeeRoleString = result.getString("role");
                EmployeeRole employeeRole = EmployeeRole.valueOf(employeeRoleString.toUpperCase());

                String employeeStatusString = result.getString("status");
                EmployeeStatus employeeStatus = EmployeeStatus.valueOf(employeeStatusString.toUpperCase());

                String employeeShiftString = result.getString("shift");
                EmployeeShift employeeShift = EmployeeShift.valueOf(employeeShiftString.toUpperCase());

                int gymId = result.getInt("gym_id");

                float salary = result.getFloat("salary");

                LocalDate hiringDate = result.getDate("hiring_date").toLocalDate();

                Employee searchedEmployee = new Employee(id, name, surname, phone, address, hiringDate, salary, employeeRole, employeeShift, employeeStatus, gymId);
                employees.add(searchedEmployee);
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

        return employees;
    }

    @Override
    public void delete(Integer key) {
        Connection connection = getConnection();
        //EMPLOYEE TIENE FOREIGN KEY CON GYM EMPLOYEES, NECESITA ON CASCADE
        String SQLSentence = "DELETE FROM employee WHERE id = ?";

        try {
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence);
            SQLSentenceObject.setInt(1, key);
            SQLSentenceObject.executeUpdate();
            SQLSentenceObject.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee searchForId(Integer key) {

        Employee searchedEmployee = null;

        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM employee WHERE ID = ?";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(SQLSentence);
            preparedStatement.setInt(1, key);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                int gymId = result.getInt("gym_id");
                String phone = result.getString("address");
                String address = result.getString("address");

                LocalDate hiringDate = result.getDate("hiring_date").toLocalDate();

                float salary = result.getFloat("salary");

                String employeeRoleString = result.getString("role");
                EmployeeRole employeeRole = EmployeeRole.valueOf(employeeRoleString.toUpperCase());

                String employeeShiftString = result.getString("shift");
                EmployeeShift employeeShift = EmployeeShift.valueOf(employeeShiftString.toUpperCase());

                String employeeStatusString = result.getString("status");
                EmployeeStatus employeeStatus = EmployeeStatus.valueOf(employeeStatusString.toUpperCase());

                searchedEmployee = new Employee(id, name, surname, address, phone, hiringDate, salary, employeeRole, employeeShift, employeeStatus, gymId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return searchedEmployee;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

    @Override
    public float updateSalary(Employee entity, float newSalary) {

        String checkId = "SELECT COUNT(*) FROM employee WHERE id=?";
        String SQLSentenceUpdate = "UPDATE employee SET salary = ? WHERE id=?";

        try (Connection connection = getConnection()) {

            try (PreparedStatement checkStmt = connection.prepareStatement(checkId)) {
                checkStmt.setInt(1, entity.getId());
                ResultSet resultSet = checkStmt.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                if (count > 0) {

                    try (PreparedStatement updateStmt = connection.prepareStatement(SQLSentenceUpdate)) {
                        updateStmt.setFloat(1, newSalary);
                        updateStmt.setInt(2, entity.getId());
                        updateStmt.executeUpdate();
                    }
                    System.out.println("Actualización de salario realizada con éxito");
                } else {
                    System.out.println("No existe empleado con ese ID: " + entity.getId());
                }

                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newSalary;
    }

    @Override
    public boolean employeeExists(Integer key) {
        Connection connection = getConnection();
        String checkSQL = "SELECT COUNT(*) FROM employee WHERE id=?";

        try {
            PreparedStatement checkSQLStatement = connection.prepareStatement(checkSQL);
            checkSQLStatement.setInt(1, key);
            ResultSet resultSet = checkSQLStatement.executeQuery();

            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
