package dao;

import entities.DTO.GymEmployeesDTO;
import entities.DTO.GymEmployeesRoleDTO;
import entities.Employee;
import entities.Gym;

import java.util.List;

public interface GymDAO extends DAO<Gym, Integer>{

    boolean gymExists(Integer key);

    List<GymEmployeesDTO> obtainGymEmployees(Integer key);
    List<GymEmployeesRoleDTO> obtainGymEmployeesByRole (Integer key, String role);


}
