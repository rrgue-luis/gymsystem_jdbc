package business;

import entities.DTO.ResultSetDto;
import entities.Gym;

import java.util.List;

public interface GymService {

    Gym insert(Gym gym);

    void delete(Integer key);

    Gym searchForId(Integer key);

    boolean gymExists(Integer key);

    void updateGym(Gym gym);

    List<Gym> obtainAll();

    List<ResultSetDto> getGymEmployees(Integer key);

    List<ResultSetDto> getGymEmployeesByRole(Integer key, String role);

    List<ResultSetDto> getGymEmployeesByShift(Integer key);


}
