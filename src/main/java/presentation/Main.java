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
        Scanner scanner = new Scanner(System.in);
        int input;
        //funcionando abstraido:

        //memberPresentation.insertMenu();
        //memberPresentation.obtainAll();
        //memberPresentation.deleteMember();
        //memberPresentation.updateMemberMenu();
        //memberPresentation.searchForIdMenu();

        do {
            System.out.println("-------MENU-------");
            System.out.println("--//--GYMSYSTEM--//--");
            System.out.println("//1 - Cargar miembro");
            System.out.println("//2 - Eliminar miembro");
            System.out.println("//3 - Editar miembro");
            System.out.println("//4 - Mostrar todos los miembros");
            System.out.println("//5 - Buscar miembro por ID");
            System.out.println("//6 - Salir \n");
            input = scanner.nextInt();

            switch(input) {
                case 1:
                    memberPresentation.insertMenu();
                    break;

                case 2:
                    memberPresentation.deleteMemberMenu();
                    break;

                case 3:
                    memberPresentation.updateMemberMenu();
                    break;

                case 4:
                    memberPresentation.obtainAllMenu();
                    break;

                case 5:
                    memberPresentation.searchForIdMenu();
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente");
                    break;

            }

        } while (input!=6);

    }



        //memberPresentation.searchForIdMenu();

}