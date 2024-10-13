package dao;

import entities.Gym;

public interface GymDAO extends DAO<Gym, Integer>{

    boolean gymExists(Integer key);

}
