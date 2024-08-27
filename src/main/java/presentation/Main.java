package presentation;

import business.MemberService;
import business.MemberServiceImp;
import dao.imp.MemberDAOImp;

public class Main {
    public static void main(String[] args) {

        /* CREAR MIEMBRO try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


            Date registrationDate = formatter.parse("2023-05-15");
            Date membershipEndDate = formatter.parse("2024-09-24");

            Member member = new Member();

            member.setName("Luis");
            member.setSurname("Carrizo");
            member.setGender("male");
            member.setPhone("1165250302");
            member.setAddress("machain");

            Scanner scanner = new Scanner(System.in);
            String birthDateScanner;

            birthDateScanner = scanner.next();
            Date birthDate = formatter.parse(birthDateScanner);


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

         */




    }
}