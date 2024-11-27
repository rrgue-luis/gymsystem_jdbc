package business.impl;

import business.GymService;
import dao.GymDAO;
import dao.imp.GymDAOImp;
import entities.DTO.ResultSetDto;
import entities.Gym;

import java.util.List;

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
    public String showName(Integer key) {

        return gymDAO.showName(key);
    }

    @Override
    public List<Gym> obtainAll() {

        List<Gym> gyms;
        gyms = gymDAO.obtainAll();

        return gyms;

    }

    @Override
    public List<ResultSetDto> getGymEmployees(Integer key) {

        List<ResultSetDto> gymEmployees;
        gymEmployees = gymDAO.getGymEmployees(key);

        return gymEmployees;
    }

    @Override
    public List<ResultSetDto> getGymEmployeesByRole(Integer key, String role) {

        List<ResultSetDto> gymEmployees;
        gymEmployees = gymDAO.getGymEmployeesByRole(key, role);
        return gymEmployees;
    }

    @Override
    public List<ResultSetDto> getGymEmployeesByShift(Integer key) {

        List<ResultSetDto> gymEmployees;
        gymEmployees = gymDAO.getGymEmployeesByShift(key);
        return gymEmployees;
    }


}
