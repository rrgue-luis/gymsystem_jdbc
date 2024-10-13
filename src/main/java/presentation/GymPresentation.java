package presentation;

import business.GymService;
import business.impl.GymServiceImp;
import entities.Gym;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class GymPresentation {

    Scanner scanner = new Scanner(System.in);

    GymService gymService = new GymServiceImp();

   public Gym insertMenu() {
       Gym gym = new Gym();

       System.out.println("Ingrese el nombre de la sucursal:");
       gym.setName(scanner.nextLine());

       System.out.println("Ingrese la direccion de la sucursal: ");
       gym.setAddress(scanner.nextLine());

       System.out.println("Ingrese el telefono de la sucursal: ");
       gym.setPhone(scanner.nextLine());

       System.out.println("Ingrese email de la sucursal: ");
       gym.setEmail(scanner.nextLine());

       Gym.status gymStatus = null;

       System.out.println("Ingrese el estado de la sucursal: ('ACTIVE', 'INACTIVE', 'MAINTENANCE')");
        gym.setstatus(scanner.nextLine());
       return gym;
   }





}
