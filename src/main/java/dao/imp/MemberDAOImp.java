package dao.imp;

import dao.MemberDAO;
import dao.MySQLDBConnection;
import entities.Member;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MemberDAOImp implements MySQLDBConnection, MemberDAO{
    //MemberDAOImp memberDAOImp = new MemberDAOImp();
    @Override
    public Member insert(Member entity) {

        Connection connection = getConnection();
        String SQLSentence = "INSERT INTO member(name, surname, gender, phone, address, birth_date, registration_date, membership_end_date, membership_type) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";


        try{
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence, Statement.RETURN_GENERATED_KEYS);

            SQLSentenceObject.setString(1, entity.getName());
            SQLSentenceObject.setString(2, entity.getSurname());
            SQLSentenceObject.setString(3, entity.getGender());
            SQLSentenceObject.setString(4, entity.getPhone());
            SQLSentenceObject.setString(5, entity.getAddress());
            SQLSentenceObject.setDate(6, java.sql.Date.valueOf(entity.getBirthDate()));
            SQLSentenceObject.setDate(7, java.sql.Date.valueOf(entity.getRegistrationDate()));
            SQLSentenceObject.setString(9, entity.getMembershipType().name());
            SQLSentenceObject.setDate(8, java.sql.Date.valueOf(entity.getMembershipEndDate()));


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
    public void update(Member entity) {

        Connection connection = getConnection();
        String SQLSentence = "UPDATE member SET name='"+entity.getName()+"' WHERE id="+entity.getId()+";";

        try{
            PreparedStatement SQLSentenceObject = connection.prepareStatement(SQLSentence);

            SQLSentenceObject.execute();
            SQLSentenceObject.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Member> obtainAll() {

        List<Member> members = new ArrayList<>();
        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM member";
        Statement ObjectSQLSentence = null;

        try {
            ObjectSQLSentence = connection.createStatement();
            ResultSet result = ObjectSQLSentence.executeQuery(SQLSentence);

            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String gender = result.getString("gender");
                String phone = result.getString("phone");
                String address = result.getString("address");
                LocalDate birthDate = result.getDate("birth_date").toLocalDate();
                LocalDate registrationDate = result.getDate("registration_date").toLocalDate();
                String membershipTypeString = result.getString("membership_type");
                Member.MembershipType membershipType = Member.MembershipType.valueOf(membershipTypeString.toUpperCase());
                LocalDate membershipEndDate = result.getDate("membership_end_date").toLocalDate();

                Member searchedMember = new Member(id, name, surname, gender, phone, address, birthDate, registrationDate, membershipType, membershipEndDate);
                members.add(searchedMember);
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

        return members;
    }


    @Override
    public void delete(Integer key) {

        Connection connection = getConnection();
        String SQLSentence = "DELETE FROM member WHERE id = ?";

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
    public Member searchForId(Integer key) {
        Member searchedMember = null;

        Connection connection = getConnection();
        String SQLSentence = "SELECT * FROM member WHERE ID = ?";
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(SQLSentence);
            preparedStatement.setInt(1, key);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String gender = result.getString("gender");
                String phone = result.getString("phone");
                String address = result.getString("address");

                LocalDate birthDate = result.getDate("birth_date").toLocalDate();
                LocalDate registrationDate = result.getDate("registration_date").toLocalDate();

                String membershipTypeString = result.getString("membership_type");
                Member.MembershipType membershipType = Member.MembershipType.valueOf(membershipTypeString.toUpperCase());

                LocalDate membershipEndDate = result.getDate("membership_end_date").toLocalDate();


                searchedMember = new Member(id, name, surname, gender, phone, address, birthDate, registrationDate, membershipType, membershipEndDate);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                } if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return searchedMember;
    }


    @Override
    public Connection getConnection() {
        return MySQLDBConnection.super.getConnection();
    }


}
