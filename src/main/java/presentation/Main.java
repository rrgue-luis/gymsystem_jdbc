package presentation;

import business.MemberService;
import business.impl.MemberServiceImp;
import entities.Member;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //PRUEBA CREACION, DESPUES ES LLAMAR A UN METODO QUE HAGA ESTE CRUD
       /* MemberService memberService = new MemberServiceImp();
        Member member = new Member();
        Scanner scanner = new Scanner(System.in);

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
            return;
        }

        LocalDate membershipEndDate = memberService.membershipEndDate(member, parsedDate);

        member.setMembershipEndDate(membershipEndDate);

        System.out.println("Fecha de vencimiento: " + membershipEndDate);


        DAO<Member, Integer> MemberDAO = new MemberDAOImp();
        boolean isInsert = MemberDAO.insert(member);

        System.out.println(isInsert);
        */

        /* PRUEBA MOSTRAR TODOS OK
        MemberService memberService = new MemberServiceImp();

        List<Member> memberList = memberService.obtainAll();
        for (Member member : memberList){
            System.out.println(member);
        }

         */

        /* BUSCAR POR ID OK
        MemberService memberService = new MemberServiceImp();

        Member searchedMember = memberService.searchForId(5);
        System.out.println(searchedMember);

         */
    }
}