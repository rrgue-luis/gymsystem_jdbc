package business.impl;

import business.MemberService;
import business.MembershipService;
import dao.MemberDAO;
import dao.MembershipDAO;
import dao.PaymentDAO;
import dao.imp.MemberDAOImp;
import dao.imp.MembershipDAOImp;
import dao.imp.PaymentDAOImp;
import entities.Member;
import entities.Payment;

public class MembershipServiceImp implements MembershipService {

    MemberDAO memberDAO = new MemberDAOImp();
    PaymentDAO paymentDAO = new PaymentDAOImp();

    MembershipDAO membershipDAO = new MembershipDAOImp();

    @Override
    public void renewMembership(Payment payment, Member member) {

        if(memberDAO.memberExists(member.getId())) {

            membershipDAO.updateMembershipStatus(member);

            if(payment.PaymentIsValid()) {

                paymentDAO.insert(payment);

            }
        }

    }
}
