package presentation;

import business.GymService;
import business.impl.GymServiceImp;
import entities.Gym;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
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

       System.out.println("Ingrese el horario de la sucursal: ");
       gym.setSchedule(scanner.nextLine());

       String input = null;
       Gym.Status gymStatus = null;
       System.out.println("Ingrese el estado de la sucursal: ('OPERATIVE', 'MAINTENANCE')");

       while(gymStatus == null) {

           input = scanner.nextLine().trim().toUpperCase();

           try {
               gymStatus = Gym.Status.valueOf(input);
               System.out.println("Estado elegido: " + gymStatus);
           }catch (IllegalArgumentException e) {
               System.out.println("Tipo de estado invalido. Intente nuevamente: ('OPERATIVE', 'MAINTENANCE')");
           }
       }

       gym.setStatus(gymStatus);
       gym = gymService.insert(gym);

       if (gym.getId() > 0) {
           System.out.println("Se creo correctamente el GYM con ID: " + gym.getId());
       } else {
           System.out.println("NO se creó al GYM");
       }

       return gym;
   }

   public void obtainAllMenu() {

       List<Gym> gyms = gymService.obtainAll();

       for(Gym gym : gyms) {
           System.out.println("---------------");
           System.out.println("ID: " + gym.getId());
           System.out.println("Nombre: " + gym.getName());
           System.out.println("Direccion: " + gym.getAddress());
           System.out.println("Horario: " + gym.getSchedule());
           System.out.println("Estado: " + gym.getStatus());
           System.out.println("Telefono: " + gym.getPhone());
           System.out.println("E-mail: " + gym.getEmail());
       }

   }



}
