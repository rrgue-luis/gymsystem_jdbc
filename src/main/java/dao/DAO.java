package dao;

import java.util.List;

public interface DAO<E, K>{

    boolean insert(E entity);
    void update(E entity);
    List<E> obtainAll();
    void delete(E entity);
    E searchForId(K key);


}
