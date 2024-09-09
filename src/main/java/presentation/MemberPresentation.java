package presentation;

import business.MemberService;
import business.impl.MemberServiceImp;
import entities.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberPresentation {

    public Member insertMenu(){

        //En java los objetos se pasan por referencia
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();
        MemberService memberService = new MemberServiceImp();

        System.out.println("Ingrese el nombre del miembro:");
        member.setName(scanner.nextLine());

        System.out.println("Ingrese el apellido del miembro:");
        member.setSurname(scanner.nextLine());

        System.out.println("Ingrese el genero del miembro:");
        member.setGender(scanner.nextLine());

        System.out.println("Ingrese el numero de telefono del miembro:");
        member.setPhone(scanner.nextLine());

        System.out.println("Ingrese la direccion del miembro:");
        member.setAddress(scanner.nextLine());


        System.out.println("Ingrese la fecha de nacimiento del miembro:");
        String parsingDate = scanner.nextLine();

        LocalDate parsedDate = memberService.parsedDate(parsingDate);
        member.setBirthDate(parsedDate);


        System.out.println("Ingrese la fecha de registro del miembro:");
        parsingDate = scanner.next();
        parsedDate = memberService.parsedDate(parsingDate);
        member.setRegistrationDate(parsedDate);

        System.out.println("Ingrese el tipo de membresía: 'DAILY', 'WEEKLY', 'MONTHLY' ");
        String input = scanner.next().toUpperCase();


        Member.MembershipType membershipType = null;

        try {
            membershipType = Member.MembershipType.valueOf(input);
            member.setMembershipType(membershipType);
            System.out.println("M.E: " + membershipType);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de membresia no valido. Intente nuevamente ('DAILY', 'WEEKLY', 'MONTHLY')");
            e.printStackTrace();
        }

        // Member se crea por primera vez en MemberPresentation (UN OBJETO SIEMPRE NACE CON new )
        // -> viaja a MemberService
        // -> viaja a MemberDao
        // -> viaja MemberService
        // -> viaja MemberPresentation

        LocalDate membershipEndDate = memberService.membershipEndDate(member, parsedDate);

        member.setMembershipEndDate(membershipEndDate);

        member = memberService.insert(member);

        if (member.getId() > 0) {
            System.out.println("SE CREO CORRECTAMENTE EL MEMBER CON EL ID #" + member.getId());
        } else {
            System.out.println("NO SE CREO CORRECTAMENTE EL MEMBER");
        }

        return member;
    }

    public List<Member> obtainAll() {

        MemberService memberService = new MemberServiceImp();

        List<Member> memberList = memberService.obtainAll();
        for (Member member : memberList){

            System.out.println("---------------------");
            System.out.println("Nombre: " + member.getName());
            System.out.println("Apellido: " + member.getSurname());
            System.out.println("Genero: " + member.getGender());
            System.out.println("Telefono: " + member.getPhone());
            System.out.println("Direccion: " + member.getAddress());
            System.out.println("Fecha de nacimiento: " + member.getBirthDate());
            System.out.println("Fecha de registro: " + member.getRegistrationDate());
            System.out.println("Fin de membresía: " + member.getMembershipEndDate());
            System.out.println("Tipo de membresía: " + member.getMembershipType());

            //proximamente llamar al boolean isMembershipActive en MemberService y devolver si está activa o no.

            System.out.println("Membresia: ACTIVA/INACTIVA");

        }
        return memberList;
    }

    public Member searchForId(Integer key) {
        Member searchedMember;

        for (int i=0; i > searchedMember; i++){

        }

        return searchedMember;
    }

}
