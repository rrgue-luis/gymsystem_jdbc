package business.impl;

import business.MemberService;
import dao.MemberDAO;
import dao.imp.MemberDAOImp;
import entities.Member;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberServiceImp implements MemberService {
//DEFINE COMO SE USAN LOS METODOS
    private final MemberDAOImp memberDAOImp;
    MemberDAO memberDAO = new MemberDAOImp();

    Scanner scanner = new Scanner(System.in);
    @Override
    public Member insert(Member member) {

        member = memberDAO.insert(member);
        return member;

    }

    @Override
    public void delete(Integer key) {

        memberDAO.delete(key);

    }



    @Override
    public LocalDate membershipEndDate(Member member, LocalDate parsedDate) {

        String membershipType = member.getMembershipType().name();

        if (membershipType.equals("DAILY")) {
            parsedDate = parsedDate.plusDays(1);
        } else if (membershipType.equals("WEEKLY")) {
            parsedDate = parsedDate.plusWeeks(1);
        } else {
            parsedDate = parsedDate.plusMonths(1);
        }
        return parsedDate;
    }

    @Override
    public void renewMembership(int memberId) {
        //usando memberDAOImp Y PAYMENT
    }

    @Override
    public void updateContactInfo(int memberId, String newContactInfo) {
        //usando memberDAOImp
    }

    @Override
    public boolean isMemberShipActive(int memberId) {
        //usando memberDAOImp
        return false;
    }


    @Override
    public Member searchForId(Integer key) {

        MemberDAO memberDAO = new MemberDAOImp();
        Member searchedMember = memberDAO.searchForId(key);
        return searchedMember;
    }

    @Override
    public void updateMember(Member member) {

        memberDAO.update(member);

    }

    @Override
    public boolean memberExists(Integer key) {

        MemberDAO memberDAO = new MemberDAOImp();
        boolean memberExists = memberDAO.memberExists(key);

        if (memberExists) {
            return true ;
        } else {
            return false;
        }
    }


    @Override
    public List<Member> obtainAll() {
        List<Member> members = new ArrayList<>();
        MemberDAO memberDAO = new MemberDAOImp();
        members = memberDAO.obtainAll();

        return members;
    }


    @Override
    public LocalDate parsedDate(String parsingDate) {

        LocalDate parsedDate = null;

        while (parsedDate == null) {

            if (parsingDate == null || parsingDate.isEmpty()) {
                System.out.println("Por favor, ingrese una fecha en el formato correcto: 'AAAA-MM-DD'");
                parsingDate = scanner.nextLine();
            }

            try {
                parsedDate = LocalDate.parse(parsingDate);
                System.out.println("Fecha OK");
            } catch (DateTimeParseException e) {
                System.out.println("ERROR de sintaxis al ingresar fecha, recuerde que el formato es: 'AAAA-MM-DD'");
                parsingDate = null;
            }
        }

        return parsedDate;
    }

    public MemberServiceImp() {
        this.memberDAOImp = new MemberDAOImp();
    }

    public MemberServiceImp(MemberDAOImp memberDAOImp) {
        this.memberDAOImp = memberDAOImp;
    }



}
