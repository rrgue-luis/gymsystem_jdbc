package dao;

import entities.Member;

public interface MemberDAO extends DAO<Member, Integer>{

    void udpateMember(Member member);
    Member findMemberById(int id);


}
