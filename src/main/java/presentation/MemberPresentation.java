package presentation;

import business.GymService;
import business.MemberService;
import business.impl.GymServiceImp;
import business.impl.MemberServiceImp;
import entities.Member;
import enums.member.MembershipType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberPresentation {

    Scanner scanner = new Scanner(System.in);
    MemberService memberService = new MemberServiceImp();
    GymService gymService = new GymServiceImp();

    public Member insertMenu(int selectedGym) {

        //En java los objetos se pasan por referencia
        Scanner scanner = new Scanner(System.in);
        Member member = new Member();

        selectedGym = setSelectedGym(selectedGym);

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

        do {
            System.out.println("Ingrese la fecha de registro: (ENTER: FECHA ACTUAL)");
            parsingDate = scanner.nextLine();

            if(parsingDate.equals("")) {
                System.out.println("Fecha de registro: Hoy " + LocalDate.now());
                parsedDate = LocalDate.now();
                break;
            } else {
                try {
                    parsedDate = LocalDate.parse(parsingDate);
                    System.out.println("Fecha asignada: " + parsingDate);
                    break;
                } catch(DateTimeParseException e){
                    System.out.println("Error de syntaxis, recuerde que el formato es: 'AAAA-MM-DD'");
                }
            }
        } while (true);


        String input = null;
        MembershipType membershipType = null;
        System.out.println("Ingrese el tipo de membresía: 'DAILY', 'WEEKLY', 'MONTHLY'");

        scanner.nextLine();

        while(membershipType == null) {

            input = scanner.nextLine().trim().toUpperCase();

            try {
                membershipType = MembershipType.valueOf(input);
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
            System.out.println("SE CREO CORRECTAMENTE AL MIEMBRO CON EL ID: " + member.getId());
            setMemberToAGym(selectedGym, member);
        } else {
            System.out.println("NO SE CREO CORRECTAMENTE AL MIEMBRO");
        }

        return member;
    }

    public void deleteMenu() {
        do {
            try {
                System.out.println("Ingrese el ID del miembro a eliminar: ");

                obtainAllMenu();

                System.out.print("ID: ");
                int option = scanner.nextInt();

                memberService.delete(option);
                System.out.println("Miembro borrado, ID: " + option);

                System.out.println("¿Desea seguir eliminando miembros? (1: SI, 0: SALIR AL MENÚ)");
                System.out.print("Opción: ");

                int continueOption = scanner.nextInt();

                if (continueOption != 1) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error");
                scanner.nextLine();
            }
        } while (true);
    }

    public void obtainAllMenu() {

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
        return;
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

    public void updateMenu() {

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

            MembershipType membershipType = null;

            try {
                membershipType = MembershipType.valueOf(inputString);
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
    }

    public int setSelectedGym(int selectedGym) {
        System.out.print("Ingrese el ID del gym (o presione Enter para mantener el actual): ");
        String inputString;
        boolean inputIsValid = false;
        do {
            inputString = scanner.nextLine().trim();

            if (inputString.equals("")) {
                if(!gymService.gymExists(selectedGym)) {
                    System.out.println("ERROR: El gimnasio no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                } else {
                    System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                    inputIsValid = true;
                }
            } else {
                try {
                    int gymId = Integer.parseInt(inputString);
                    if(!gymService.gymExists(gymId)) {
                        System.out.println("EL gimnasio ingresado no existe. Intente nuevamente (o presione Enter para mantener el actual)");
                    } else {
                        selectedGym = gymId;
                        System.out.println("Gimnasio seleccionado: " + selectedGym + "" + gymService.showName(selectedGym));
                        inputIsValid = true;
                    }
                } catch (NumberFormatException e){
                    System.out.println("Error: EL dato ingresado no es un numero valido. Intente nuevamente con un numero o ENTER para mantener el actual");

                }
            }

        }while (!inputIsValid);

        return selectedGym;
    }

    public void setMemberToAGym(int selectedgym, Member member) {
        System.out.println("Asignando miembro " + member + "al GYM: " + gymService.showName(selectedgym));
        gymService.setMemberToAGym(selectedgym, member);
    }
}
