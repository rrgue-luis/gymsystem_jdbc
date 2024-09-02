package dao;

import entities.Member;

import java.util.List;

public interface MemberDAO extends DAO<Member, Integer>{

    void udpateMember(Member member);
    Member findMemberById(int id);




}
