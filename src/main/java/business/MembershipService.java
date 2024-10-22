package business;

import entities.Member;
import entities.Payment;

public interface MembershipService {

    public void renewMembership(Payment payment, Member member);

}
