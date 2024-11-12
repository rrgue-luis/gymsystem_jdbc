package presentation;

import business.GymService;
import business.impl.GymServiceImp;
import entities.DTO.GymEmployeeShiftDTO;
import entities.DTO.GymEmployeesDTO;
import entities.DTO.GymEmployeesRoleDTO;
import entities.Gym;

import java.util.InputMismatchException;
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

   public void searchForIdMenu() {

       Gym searchedGym;
       int input;

       do {
           System.out.println("Ingrese el ID del gym a buscar: ");
           input = scanner.nextInt();

           searchedGym = gymService.searchForId(input);

           if(searchedGym != null) {
               System.out.println(searchedGym);
           } else {
               System.out.println("NO existe ese gym, intente con otro ID. \n Volver al menú: --(0)--");
           }

           if(input == 0) {
               System.out.println("Volviendo al menú.");
               break;
           }

       } while (searchedGym == null);

   }

   public void deleteMenu() {

       boolean gymExists;

       do {
           System.out.println("Ingrese el ID del GYM a eliminar: ");
           obtainAllMenu();
           System.out.println("-----------LISTA-----------");
           int input = scanner.nextInt();

           gymExists = gymService.gymExists(input);

           if (gymService.gymExists(input)) {
               System.out.println("GYM eliminado: " + input);
               gymService.delete(input);
           } else {
               System.out.println("No existe GYM con ese ID, intente nuevamente");
           }

       } while(!gymExists);

   }

   public void updateMenu() {

       System.out.println("Ingrese el ID del GYM a actualizar: ");
       obtainAllMenu();
       int input = scanner.nextInt();

       boolean gymExists = gymService.gymExists(input);

       if(gymExists) {
           Gym gym = new Gym();

           gym.setId(input);
           System.out.println("Ingrese el nombre del GYM: ");
           scanner.nextLine();
           gym.setName(scanner.nextLine());

           System.out.println("Ingrese la direccion del GYM: ");
           gym.setAddress(scanner.nextLine());

           System.out.println("Ingrese el horario del GYM: ");
           gym.setSchedule(scanner.nextLine());

           System.out.println("Ingrese el telefono del GYM: ");
           gym.setPhone(scanner.nextLine());

           System.out.println("Ingrese el e-mail del GYM: ");
           gym.setEmail(scanner.nextLine());


           String status = null;
           Gym.Status gymStatus = null;
           System.out.println("Ingrese el estado del GYM: ('OPERATIVE', 'MAINTENANCE')");

           while(gymStatus == null) {

               status = scanner.nextLine().trim().toUpperCase();

               try {
                   gymStatus = Gym.Status.valueOf(status);
                   System.out.println("Estado elegido: " + gymStatus);
               }catch (IllegalArgumentException e) {
                   System.out.println("Tipo de estado invalido. Intente nuevamente: ('OPERATIVE', 'MAINTENANCE')");
                   e.printStackTrace();
               }
           }

           gym.setStatus(gymStatus);
           gymService.updateGym(gym);

       }

   }

   //EMPLOYEES

   public void listGymEmployeesMenu() {
       System.out.println("Ingrese el ID del gym del que desea saber sus empleados: ");
       int input = scanner.nextInt();

       List<GymEmployeesDTO> gymEmployeesList;
       gymEmployeesList = gymService.obtainGymEmployees(input);

       for(GymEmployeesDTO gymEmployees : gymEmployeesList) {
           System.out.println("------------------");
           System.out.println("Empleado ID: " + gymEmployees.getEmployeeId());
           System.out.println("Nombre de empleado: " + gymEmployees.getEmployeeName());
           System.out.println("Gym ID: " + gymEmployees.getGymId());
           System.out.println("Gym nombre: " + gymEmployees.getGymName());
       }

   }

   public void listGymEmployeesByRoleMenu() {
       System.out.println("MOSTRAR MIEMBROS POR ROL");
       System.out.println("Ingrese el ID del gym: ");
       int option = scanner.nextInt();

       scanner.nextLine();

       System.out.println("Por que rol desea ordenar?: ('BOSS', 'MANAGER', 'TRAINER', 'EMPLOYEE')");
       String input = scanner.nextLine();

       List<GymEmployeesRoleDTO> employeesRoleList;

       employeesRoleList = gymService.obtainGymEmployeesByRole(option, input);
       if (employeesRoleList.isEmpty()) {
           System.out.println("No se encontraron empleados con el rol especificado en el gimnasio seleccionado.");
       } else {
           for (GymEmployeesRoleDTO gymEmployees : employeesRoleList) {
               System.out.println("------------------");
               System.out.println("Rol: " + gymEmployees.getEmployeeRole());
               System.out.println("Empleado ID: " + gymEmployees.getEmployeeId());
               System.out.println("Nombre de empleado: " + gymEmployees.getEmployeeName());
               System.out.println("Gym ID: " + gymEmployees.getGymId());
               System.out.println("Gym nombre: " + gymEmployees.getGymName());
           }
       }

   }

   public void listGymEmployeesByShiftMenu() {
       System.out.println("----ORDENAR EMPLEADOS POR TURNO----");

       boolean gymExists = false;
       int input;

       do {
          try {

              System.out.println("Ingrese el ID del GYM");
              input = scanner.nextInt();
              gymExists = gymService.gymExists(input);

              if (gymExists) {
                  List<GymEmployeeShiftDTO> gymEmployees = gymService.obtainGymEmployeesByShift(input);

                  System.out.println("---- LISTADO DE EMPLEADOS ORDENADO POR TURNO ----");
                  for (GymEmployeeShiftDTO employeesShifts : gymEmployees) {
                      System.out.println("------------------");
                      System.out.println("Turno: " + employeesShifts.getEmployeeShift());
                      System.out.println("Empleado ID: " + employeesShifts.getEmployeeId());
                      System.out.println("Nombre de empleado: " + employeesShifts.getEmployeeName());
                      System.out.println("Gym ID: " + employeesShifts.getGymId());
                      System.out.println("Gym nombre: " + employeesShifts.getGymName());
                  }

              } else {
                  System.out.println("No existe un gimnasio con ese ID. Inténtelo nuevamente.");
              }
          }catch(InputMismatchException e) {
              System.out.println("Entrada invalida, recuerde que Gym ID es un numero!");
              scanner.nextLine();
          }

       } while (!gymExists);

   }

   public void searchEmployeeInGymMenu() {

   }

   public void assignEmployeeToGymMenu() {

   }

   public void removeEmployeeFromGymMenu() {

   }
    //GYMS
   public void generateGymReportMenu() {

   }
   public void viewGymStatisticsMenu() {
       //Muestra estadísticas del gimnasio, como el promedio de miembros diarios, membresías activas o ingresos por membresía.
   }




}
