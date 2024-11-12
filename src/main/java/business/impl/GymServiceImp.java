package business.impl;

import business.GymService;
import dao.GymDAO;
import dao.imp.GymDAOImp;
import entities.DTO.GymEmployeeShiftDTO;
import entities.DTO.GymEmployeesDTO;
import entities.DTO.GymEmployeesRoleDTO;
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

    @Override
    public List<GymEmployeesRoleDTO> obtainGymEmployeesByRole(Integer key, String role) {

        List<GymEmployeesRoleDTO> gymEmployeesRoles;
        gymEmployeesRoles = gymDAO.obtainGymEmployeesByRole(key, role);
        return gymEmployeesRoles;
    }

    @Override
    public List<GymEmployeeShiftDTO> obtainGymEmployeesByShift(Integer key) {

        List<GymEmployeeShiftDTO> gymEmployees;
        gymEmployees = gymDAO.obtainGymEmployeesByShift(key);
        return gymEmployees;
    }


}
