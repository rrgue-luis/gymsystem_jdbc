package business;

import entities.Gym;

import java.time.LocalDate;
import java.util.List;

public interface GymService {

    Gym insert(Gym gym);

    public void delete(Integer key);

    Gym searchForId(Integer key);

    public boolean gymExists(Gym gym);

    void updateGym(Gym gym);

    List<Gym> obtainAll();

    LocalDate parseDate(String parsingDate);

}
