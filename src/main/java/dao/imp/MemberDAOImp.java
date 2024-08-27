package dao.imp;

import com.sun.jdi.connect.Connector;
import dao.DAO;
import dao.MemberDAO;
import dao.MySQLDBConnection;
import entities.Member;

import java.sql.*;
import java.util.List;

//String SQLSentence = "INSERT INTO member(name, surname, gender, phone, address, birth_date, registration_date, membership_end_date, membership_type) " +
//                "VALUES('"+entity.getName()+", "+entity.getSurname()+", "+entity.getGender()+", "+entity.getPhone()+", "+entity.getAddress()+", "+entity.getBirthDate()+", "+entity.getRegistrationDate()+", "+entity.getMembershipEndDate()+", "+entity.getMembershipType()+"')";
public class MemberDAOImp implements MySQLDBConnection, MemberDAO{
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

        Connection connection = getConnection();
        String SQLSentence = "UPDATE member SET name='"+entity.getName()+"'WHERE id="+entity.getId()+";";

        try {

            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence);

            SQLSentenceObject.execute();
            SQLSentenceObject.close();

        } catch(SQLException e){
            e.printStackTrace();
        }

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

        Member searchedMember = null;

        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM member WHERE ID = "+key;
        Statement ObjectSQLSentence = null;

        try {

            ObjectSQLSentence = connection.createStatement();
            ResultSet result = ObjectSQLSentence.executeQuery(SQLSentence);

            while(result.next()) {
                int id = result.getInt("ID: ");
                String name = result.getString("name: ");
                String surname = result.getString("surname: ");
                String phone = result.getString("phone: ");
                String address = result.getString("address: ");

                /*searchedMember.setBirthDate(birthDate);
                searchedMember.setRegistrationDate(registrationDate);
                searchedMember.setMembershipEndDate(membershipEndDate);
                searchedMember.setMembershipType(Member.MembershipType.MONTHLY);

                searchedMember = new Member(id, name, surname, phone, address, );

                 */
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }
    @Override
    public void udpateMember(Member member) {
    }

    @Override
    public Member findMemberById(int id) {
        return null;
    }
}
