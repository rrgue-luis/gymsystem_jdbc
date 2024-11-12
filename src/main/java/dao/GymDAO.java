package dao;

import entities.DTO.GymEmployeeShiftDTO;
import entities.DTO.GymEmployeesDTO;
import entities.DTO.GymEmployeesRoleDTO;
import entities.Gym;

import java.util.List;

public interface GymDAO extends DAO<Gym, Integer>{

    boolean gymExists(Integer key);

    List<GymEmployeesDTO> obtainGymEmployees(Integer key);
    List<GymEmployeesRoleDTO> obtainGymEmployeesByRole (Integer key, String role);

    List<GymEmployeeShiftDTO> obtainGymEmployeesByShift(Integer key);


}
