package business.impl;

import business.GymService;
import dao.GymDAO;
import dao.imp.EmployeeDAOImp;
import dao.imp.GymDAOImp;
import entities.Employee;
import entities.Gym;
import entities.GymEmployeesDTO;
import entities.Payment;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymServiceImp implements GymService {

    private final GymDAOImp gymDAOImp;

    GymDAO gymDAO = new GymDAOImp();

    public GymServiceImp() {
        this.gymDAOImp = new GymDAOImp();
    }

    public GymServiceImp(GymDAOImp gymDAOImp) {
        this.gymDAOImp = gymDAOImp;
    }

    @Override
    public Gym insert(Gym gym) {

        gym = gymDAO.insert(gym);
        return gym;

    }

    @Override
    public void delete(Integer key) {
        gymDAO.delete(key);
    }

    @Override
    public Gym searchForId(Integer key) {
        return gymDAO.searchForId(key);
    }

    @Override
    public boolean gymExists(Integer key) {

        boolean gymExists = gymDAO.gymExists(key);

        if(gymExists) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void updateGym(Gym gym) {
        gymDAO.update(gym);
    }

    @Override
    public List<Gym> obtainAll() {

        List<Gym> gyms;
        gyms = gymDAO.obtainAll();

        return gyms;

    }

    @Override
    public List<GymEmployeesDTO> obtainGymEmployees(Integer key) {

        List<GymEmployeesDTO> gymEmployees;
        gymEmployees = gymDAO.obtainGymEmployees(key);

        return gymEmployees;
    }


}
