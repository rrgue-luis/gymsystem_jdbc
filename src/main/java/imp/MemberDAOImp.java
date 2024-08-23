package imp;

import dao.DAO;
import dao.MySQLDBConnection;
import entities.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
//String SQLSentence = "INSERT INTO member(name, surname, gender, phone, address, birth_date, registration_date, membership_end_date, membership_type) " +
//                "VALUES('"+entity.getName()+", "+entity.getSurname()+", "+entity.getGender()+", "+entity.getPhone()+", "+entity.getAddress()+", "+entity.getBirthDate()+", "+entity.getRegistrationDate()+", "+entity.getMembershipEndDate()+", "+entity.getMembershipType()+"')";
public class MemberDAOImp implements MySQLDBConnection, DAO<Member, Integer> {
    @Override
    public boolean insert(Member entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO member(name, surname, gender, phone, address, birth_date, registration_date, membership_end_date, membership_type) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence);

            SQLSentenceObject.setString(1, entity.getName());
            SQLSentenceObject.setString(2, entity.getSurname());
            SQLSentenceObject.setString(3, entity.getGender());
            SQLSentenceObject.setString(4, entity.getPhone());
            SQLSentenceObject.setString(5, entity.getAddress());
            SQLSentenceObject.setDate(6, new java.sql.Date(entity.getBirthDate().getTime()));
            SQLSentenceObject.setDate(7, new java.sql.Date(entity.getRegistrationDate().getTime()));
            SQLSentenceObject.setDate(8, new java.sql.Date(entity.getMembershipEndDate().getTime()));
            SQLSentenceObject.setString(9, entity.getMembershipType().name());

            int rosInserted = SQLSentenceObject.executeUpdate();
            //SQLSentenceObject.execute();
            SQLSentenceObject.close();

            return rosInserted > 0;

        }catch(SQLException e){

            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void update(Member entity) {

    }

    @Override
    public List<Member> obtainAll() {
        return null;
    }

    @Override
    public void delete(Member entity) {

    }

    @Override
    public Member searchForId(Integer key) {
        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }
}
