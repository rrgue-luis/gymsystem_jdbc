package business;

import entities.Member;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//DEFINE QUE METODOS EXISTEN EN MEMBER
public interface MemberService {



    LocalDate membershipEndDate(Member member, LocalDate parsedDate);

    List<Member> obtainAll();
    void renewMembership(int memberId);

    void updateContactInfo(int memberId, String newContactInfo);

    Member getMemberDetails(int memberId);

    public boolean isMemberShipActive(int memberId);

    String formattedBirthDate = null;

    LocalDate parsedDate(String parsingDate);


}
