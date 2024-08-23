package entities;

import dao.DAO;
import imp.MemberDAOImp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date birthDate = formatter.parse("2003-05-17");
            java.util.Date registrationDate = formatter.parse("2023-05-15");
            java.util.Date membershipEndDate = formatter.parse("2024-09-24");

            Member member = new Member();

            member.setName("Luis");
            member.setSurname("Carrizo");
            member.setGender("male");
            member.setPhone("1165250302");
            member.setAddress("machain");
            member.setBirthDate(birthDate);
            member.setRegistrationDate(registrationDate);
            member.setMembershipEndDate(membershipEndDate);
            member.setMembershipType(Member.MembershipType.MONTHLY);

            DAO<Member, Integer> MemberDAO = new MemberDAOImp();
            boolean isInsert = MemberDAO.insert(member);

            System.out.println(isInsert);

        } catch (ParseException e) {
            e.printStackTrace();
        }






    }
}