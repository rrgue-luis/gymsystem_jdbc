package dao;

import entities.Employee;
import entities.Gym;
import entities.GymEmployeesDTO;

import java.util.List;

public interface GymDAO extends DAO<Gym, Integer>{

    boolean gymExists(Integer key);

    List<GymEmployeesDTO> obtainGymEmployees(Integer key);

}
