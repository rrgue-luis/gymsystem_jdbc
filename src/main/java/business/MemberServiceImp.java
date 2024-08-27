package business;

import dao.imp.MemberDAOImp;
import entities.Member;

public class MemberServiceImp implements MemberService{
//DEFINE COMO SE USAN LOS METODOS
    private final MemberDAOImp memberDAOImp;

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

    public void mostraralgo(){

    }

    public MemberServiceImp() {
        memberDAOImp = null;
    }
    public MemberServiceImp(MemberDAOImp memberDAOImp) {
        this.memberDAOImp = memberDAOImp;
    }
}
