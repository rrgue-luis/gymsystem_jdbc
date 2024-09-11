package business.impl;

import business.MemberService;
import dao.DAO;
import dao.MemberDAO;
import dao.imp.MemberDAOImp;
import entities.Member;
import presentation.MemberPresentation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberServiceImp implements MemberService {
//DEFINE COMO SE USAN LOS METODOS
    private final MemberDAOImp memberDAOImp;
    @Override
    public Member insert(Member member) {
        MemberDAO memberDAO = new MemberDAOImp();

        member = memberDAO.insert(member);

       /* boolean isInserted;
        if (member.getId() > 0) {
            isInserted = true;
        } else {
            isInserted = false;
        }*/

        return member;
    }

    @Override
    public void delete(Integer key) {
        MemberDAO memberDAO = new MemberDAOImp();

        memberDAO.delete(key);

    }

    @Override
    public LocalDate membershipEndDate(Member member, LocalDate parsedDate) {

        int membershipTypeNumber;
        String membershipType = member.getMembershipType().name();

        if(membershipType.equals("DAILY")){
             membershipTypeNumber = 1;
        }else if(membershipType.equals("WEEKLY")){
            membershipTypeNumber = 7;
        }else{
            membershipTypeNumber = 30;
        }

        return parsedDate.plusDays(membershipTypeNumber);
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
    public List<Member> obtainAll() {
        List<Member> members = new ArrayList<>();
        MemberDAO memberDAO = new MemberDAOImp();
        members = memberDAO.obtainAll();

        return members;
    }


    @Override
    public LocalDate parsedDate(String parsingDate){

        try {
            LocalDate parsedDate = LocalDate.parse(parsingDate);
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }

    public MemberServiceImp() {
        this.memberDAOImp = new MemberDAOImp();
    }

    public MemberServiceImp(MemberDAOImp memberDAOImp) {
        this.memberDAOImp = memberDAOImp;
    }



}
