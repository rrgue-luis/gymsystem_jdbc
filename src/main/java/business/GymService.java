package business;

import entities.Employee;
import entities.Gym;
import entities.GymEmployeesDTO;
import entities.Payment;

import java.time.LocalDate;
import java.util.List;

public interface GymService {

    Gym insert(Gym gym);

    void delete(Integer key);

    Gym searchForId(Integer key);

    boolean gymExists(Integer key);

    void updateGym(Gym gym);

    List<Gym> obtainAll();

    List<GymEmployeesDTO> obtainGymEmployees(Integer key);


}
