package business;

import entities.DTO.GymEmployeesDTO;
import entities.DTO.GymEmployeesRoleDTO;
import entities.Employee;
import entities.Gym;

import java.util.List;

public interface GymService {

    Gym insert(Gym gym);

    void delete(Integer key);

    Gym searchForId(Integer key);

    boolean gymExists(Integer key);

    void updateGym(Gym gym);

    List<Gym> obtainAll();

    List<GymEmployeesDTO> obtainGymEmployees(Integer key);

    List<GymEmployeesRoleDTO> obtainGymEmployeesByRole(Integer key, String role);


}
