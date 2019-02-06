package ua.lviv.iot.lab3.persistence.dao;

public interface IDao<T> {

    T findById(Integer id);

    T persist (T object);

    void remove(Integer id);

    T update(T object);
}

//DAO (Data Access Object) - a layer of objects that
//provide access to the data.

//Usually DAO implementation uses EntityManager
//and with its help we work with our database