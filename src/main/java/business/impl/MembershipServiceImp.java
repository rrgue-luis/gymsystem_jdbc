package business.impl;

import business.MembershipService;
import dao.MemberDAO;
import dao.PaymentDAO;
import dao.imp.MemberDAOImp;
import dao.imp.PaymentDAOImp;
import entities.Member;
import entities.Payment;

public class MembershipServiceImp implements MembershipService {

    MemberDAO memberDAO = new MemberDAOImp();
    PaymentDAO paymentDAO = new PaymentDAOImp();

    @Override
    public void renewMembership(Payment payment, Member member) {

        if(memberDAO.memberExists(member.getId())) {

            memberDAO.update(member);

            if(payment.PaymentIsValid()) {

                paymentDAO.insert(payment);

            }
        }

    }
}
