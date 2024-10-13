package business.impl;

import business.GymService;
import dao.GymDAO;
import dao.imp.EmployeeDAOImp;
import dao.imp.GymDAOImp;
import entities.Gym;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GymServiceImp implements GymService {

    Scanner scanner = new Scanner(System.in);

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
        return null;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public Gym searchForId(Integer key) {
        return null;
    }

    @Override
    public boolean gymExists(Gym gym) {
        return false;
    }

    @Override
    public void updateGym(Gym gym) {

    }

    @Override
    public List<Gym> obtainAll() {
        return null;
    }

    @Override
    public LocalDate parseDate(String parsingDate) {
        return null;
    }
}
