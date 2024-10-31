package dao.imp;

import dao.MembershipDAO;
import dao.MySQLDBConnection;
import entities.Member;
import entities.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MembershipDAOImp implements MySQLDBConnection, MembershipDAO {
    @Override
    public Payment insert(Payment entity) {
        return null;
    }

    @Override
    public void update(Payment entity) {

    }

    @Override
    public List<Payment> obtainAll() {
        return null;
    }

    @Override
    public void delete(Member key) {

    }

    @Override
    public Payment searchForId(Member key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }

   /* POR EL MOMENTO MEMBERSHIPDAO NO HACE NADA, NO FUE NECESARIO
   @Override
    public void updateMembershipStatus(Member entity) {
        Connection connection = getConnection();
        String checkId = "SELECT COUNT(*) FROM member WHERE id=?";
        String SQLSentenceUpdateMember = "UPDATE member SET membership_end_date=?, membership_type=? WHERE id = ?;";

        try {
            PreparedStatement checkStmt = connection.prepareStatement(checkId);
            checkStmt.setInt(1, entity.getId());
            ResultSet result = checkStmt.executeQuery();

            result.next();
            int count = result.getInt(1);

            if(count>0) {
                PreparedStatement updateStatement = connection.prepareStatement(SQLSentenceUpdateMember);
                updateStatement.setDate(1, java.sql.Date.valueOf(entity.getMembershipEndDate()));
                updateStatement.setString(2, entity.getMembershipType().name());
                updateStatement.setInt(3, entity.getId());

                updateStatement.executeUpdate();
                updateStatement.close();
            }
            result.close();
            checkStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    */
}
