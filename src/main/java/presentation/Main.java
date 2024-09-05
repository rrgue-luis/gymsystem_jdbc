package presentation;

import business.MemberService;
import business.impl.MemberServiceImp;
import entities.Member;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        MemberPresentation memberPresentation = new MemberPresentation();
        memberPresentation.insertMenu();


        /* MemberService memberService = new MemberServiceImp();
        Member member = new Member();
        memberService.insert(member);
         */


        // PRUEBA MOSTRAR TODOS OK


        /*List<Member> memberList = memberService.obtainAll();
        for (Member member : memberList){
            System.out.println(member);
        }

         */

/*
        MemberService memberService = new MemberServiceImp();

        Member searchedMember = memberService.searchForId(5);
        System.out.println(searchedMember);


 */

        /*BORRAR OK

        MemberService memberService = new MemberServiceImp();

        Member searchedMember = memberService.searchForId(15);

        memberService.delete(searchedMember);


         */



    }
}