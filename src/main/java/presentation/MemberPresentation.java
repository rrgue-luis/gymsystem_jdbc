package presentation;

import business.MemberService;
import business.impl.MemberServiceImp;
import entities.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberPresentation {

    Scanner scanner = new Scanner(System.in);
    MemberService memberService = new MemberServiceImp();

    public Member insertMenu(){

        //En java los objetos se pasan por referencia
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();


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

        String input = null;
        Member.MembershipType membershipType = null;
        System.out.println("Ingrese el tipo de membresía: 'DAILY', 'WEEKLY', 'MONTHLY'");

        scanner.nextLine();

        while(membershipType == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                membershipType = Member.MembershipType.valueOf(input);
                System.out.println("Membresía elegida: " + membershipType);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de membresía no valido. Intente nuevamente ('DAILY', 'WEEKLY', 'MONTHLY'):");
            }

        }

        member.setMembershipType(membershipType);


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

    public void deleteMemberMenu() {

        System.out.println("Ingrese el ID del miembro a eliminar: ");
        obtainAllMenu();
        int option = scanner.nextInt();
        memberService.delete(option);
        System.out.println("Miembro borrado, ID: " + option);

    }

    public List<Member> obtainAllMenu() {

        List<Member> memberList = memberService.obtainAll();
        for (Member member : memberList){

            System.out.println("---------------------");
            System.out.println("ID: " + member.getId());
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


    /**
     * TEMPORAL, PARA TESTING
     */
    public void memberExistsMenu(){
        System.out.println("Ingrese el id a corroborar que exista: ");
        int input = scanner.nextInt();
        boolean memberExists = memberService.memberExists(input);
        if (memberExists) {

            System.out.println("Existe.");
        } else {
            System.out.println("No existe");
        }
    }

    public void updateMemberMenu() {

        System.out.println("Ingrese el ID del miembro a modificar: ");
        int input = scanner.nextInt();
        scanner.nextLine();
        boolean memberExists = memberService.memberExists(input);


        if(memberExists) {

            Member member = new Member();

            member.setId(input);
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
            parsingDate = scanner.nextLine();
            parsedDate = memberService.parsedDate(parsingDate);
            member.setRegistrationDate(parsedDate);

            LocalDate membershipEndDate = parsedDate;

            member.setMembershipEndDate(membershipEndDate);

            System.out.println("Ingrese el tipo de membresía: 'DAILY', 'WEEKLY', 'MONTHLY' ");
            String inputString = scanner.nextLine().toUpperCase();



            Member.MembershipType membershipType = null;

            try {
                membershipType = Member.MembershipType.valueOf(inputString);
                member.setMembershipType(membershipType);
                System.out.println("M.E: " + membershipType);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de membresia no valido. Intente nuevamente ('DAILY', 'WEEKLY', 'MONTHLY')");
                e.printStackTrace();
            }

            memberService.updateMember(member);

        } else {
            System.out.println("El miembro no existe");
        }

    }

    public void searchForIdMenu() {
        Member searchedMember;

        System.out.println("Ingrese un ID a buscar");

        int input = scanner.nextInt();

        searchedMember = memberService.searchForId(input);
        System.out.println(searchedMember);

    }

}
