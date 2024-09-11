package dao;

import java.util.List;

public interface DAO<E, K>{

    E insert(E entity);
    void update(E entity);
    List<E> obtainAll();
    void delete(K key);
    E searchForId(K key);


}
