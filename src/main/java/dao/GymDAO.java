package dao;

import entities.DTO.ResultSetDto;
import entities.Gym;
import entities.Payment;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface GymDAO extends DAO<Gym, Integer>{

    boolean gymExists(Integer key);

    List<ResultSetDto> getGymEmployees(Integer key);
    List<ResultSetDto> getGymEmployeesByRole (Integer key, String role);
    List<ResultSetDto> getGymEmployeesByShift(Integer key);
    String showName(Integer key);

}
