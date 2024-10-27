package dao.imp;

import dao.DAO;
import dao.MySQLDBConnection;
import dao.PaymentDAO;
import entities.Member;
import entities.Payment;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImp implements MySQLDBConnection, PaymentDAO {
    @Override
    public Payment insert(Payment entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO payment(member_id, amount, payment_date, payment_method, payment_is_valid) " + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence, Statement.RETURN_GENERATED_KEYS);

            SQLSentenceObject.setInt(1, entity.getMemberId());
            SQLSentenceObject.setFloat(2, entity.getAmount());
            SQLSentenceObject.setDate(3, java.sql.Date.valueOf(entity.getPaymentDate()));
            SQLSentenceObject.setString(4, entity.getPaymentMethod().name());
            SQLSentenceObject.setBoolean(5, entity.PaymentIsValid());

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
    public void update(Payment entity) {

    }

    @Override
    public List<Payment> obtainAll() {
        List<Payment> payments = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM payment";
        Statement ObjectSQLSentence = null;

        try {
            ObjectSQLSentence = connection.createStatement();
            ResultSet result = ObjectSQLSentence.executeQuery(SQLSentence);

            while(result.next()) {
                int id = result.getInt("id");
                int memberId = result.getInt("member_id");
                float amount = result.getFloat("amount");
                LocalDate paymentDate = result.getDate("payment_date").toLocalDate();
                String paymentMethodString = result.getString("payment_method");
                Payment.PaymentMethod paymentMethod = Payment.PaymentMethod.valueOf(paymentMethodString.toUpperCase());
                Boolean paymentIsValid = result.getBoolean("payment_is_valid");

                Payment searchedPayment = new Payment(id, amount, paymentDate, paymentMethod, memberId, paymentIsValid);
                payments.add(searchedPayment);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ObjectSQLSentence.close();
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return payments;
    }

    @Override
    public void delete(Integer key) {
        Connection connection = getConnection();
        String SQLSentence = "DELETE FROM payment WHERE id=?";

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
    public Payment searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

    @Override
    public boolean paymentExists(Integer key) {
        Connection connection = getConnection();
        String checkSQL = "SELECT COUNT(*) FROM payment WHERE id=?";

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

    @Override
    public List<Payment> listMemberPayments(Integer key) {

        List<Payment> memberPayments = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM payment WHERE member_id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQLSentence);
            preparedStatement.setInt(1, key);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                int id = result.getInt("id");
                int memberId = result.getInt("member_id");
                float amount = result.getFloat("amount");
                LocalDate paymentDate = result.getDate("payment_date").toLocalDate();
                String stringPaymentMethod = result.getString("payment_method");
                Payment.PaymentMethod paymentMethod = Payment.PaymentMethod.valueOf(stringPaymentMethod.toUpperCase());
                boolean paymentIsValid = result.getBoolean("payment_is_valid");

                Payment searchedPayment = new Payment(id, amount, paymentDate, paymentMethod, memberId, paymentIsValid);
                memberPayments.add(searchedPayment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return memberPayments;

    }
}
