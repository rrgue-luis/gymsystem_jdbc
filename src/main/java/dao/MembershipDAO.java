package dao;

import entities.Member;
import entities.Payment;

public interface MembershipDAO extends DAO<Payment, Member>{

    void updateMembershipStatus(Member entity);

}
