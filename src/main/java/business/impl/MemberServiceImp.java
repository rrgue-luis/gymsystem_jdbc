package business.impl;

import business.MemberService;
import dao.imp.MemberDAOImp;
import entities.Member;
import dao.MySQLDBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImp implements MemberService {
//DEFINE COMO SE USAN LOS METODOS
    private final MemberDAOImp memberDAOImp;
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
        //usando memberDAOImp
    }

    @Override
    public void updateContactInfo(int memberId, String newContactInfo) {
        //usando memberDAOImp
    }

    @Override
    public Member getMemberDetails(int memberId) {
        //usando memberDAOImp
        return null;
    }

    @Override
    public boolean isMemberShipActive(int memberId) {
        //usando memberDAOImp
        return false;
    }

    @Override
    public Member searchForId(Integer key) {

        MemberDAOImp memberDAO = new MemberDAOImp();
        Member searchedMember = memberDAO.searchForId(key);
        return searchedMember;
    }

    @Override
    public List<Member> obtainAll() {
        List<Member> members = new ArrayList<>();
        MemberDAOImp memberDAOImp = new MemberDAOImp();
        members = memberDAOImp.obtainAll();

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

    public void parseMembershipEndDate(){

    }

    public MemberServiceImp() {
        this.memberDAOImp = new MemberDAOImp();
    }

    public MemberServiceImp(MemberDAOImp memberDAOImp) {
        this.memberDAOImp = memberDAOImp;
    }
}
