package business;

import entities.Member;
import entities.Payment;
import java.util.Date;
//DEFINE QUE METODOS EXISTEN EN MEMBER
public interface MemberService {

    void renewMembership(int memberId);

    void updateContactInfo(int memberId, String newContactInfo);

    Member getMemberDetails(int memberId);

    public boolean isMemberShipActive(int memberId);

    String formattedBirthDate = null;

}
