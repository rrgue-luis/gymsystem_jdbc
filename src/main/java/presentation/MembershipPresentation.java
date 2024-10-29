package presentation;

import business.MemberService;
import business.MembershipService;
import business.impl.MemberServiceImp;
import business.impl.MembershipServiceImp;
import entities.Member;
import entities.Payment;

import java.time.LocalDate;
import java.util.Scanner;

public class MembershipPresentation {

    Scanner scanner = new Scanner(System.in);
    MemberService memberService = new MemberServiceImp();

    MembershipService membershipService = new MembershipServiceImp();
    PaymentPresentation paymentPresentation = new PaymentPresentation();

    public void renewMembership() {

        Member member = new Member();

        System.out.println("Ingrese el ID del miembro a renovar: ");
        int input = scanner.nextInt();
        scanner.nextLine();
        member.setId(input);

        boolean memberExists = memberService.memberExists(member.getId());

        if(memberExists) {
            System.out.println("Ingrese el tipo de membresía deseada: 'DAILY', 'WEEKLY', 'MONTHLY' ");
            String membershipTypeString = scanner.nextLine().toUpperCase();

            Member.MembershipType membershipType = null;

            try {
                membershipType = Member.MembershipType.valueOf(membershipTypeString);
                member.setMembershipType(membershipType);
                System.out.println("M.E: " + membershipType);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de membresia no valido. Intente nuevamente ('DAILY', 'WEEKLY', 'MONTHLY')");
                e.printStackTrace();
            }

            LocalDate parsedDate = LocalDate.now();

            member.setMembershipEndDate(memberService.membershipEndDate(member, parsedDate));

            Payment payment;
            payment = paymentPresentation.insertMenu();

            membershipService.renewMembership(payment, member);

        } else {
            System.out.println("No existe miembro con ese ID. Intente nuevamente: ");
        }

    }

}
