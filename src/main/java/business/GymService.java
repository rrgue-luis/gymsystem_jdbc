package business;

import entities.DTO.ResultSetDto;
import entities.Gym;
import entities.Member;
import entities.Payment;

import java.util.List;

public interface GymService {

    Gym insert(Gym gym);

    void delete(Integer key);

    Gym searchForId(Integer key);

    boolean gymExists(Integer key);

    void updateGym(Gym gym);

    String showName(Integer key);

    List<Gym> obtainAll();

    void setMemberToAGym(Integer key, Member member);

    List<ResultSetDto> getGymEmployees(Integer key);

    List<ResultSetDto> getGymEmployeesByRole(Integer key, String role);

    List<ResultSetDto> getGymEmployeesByShift(Integer key);


}
