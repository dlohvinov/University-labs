package ua.lviv.iot.lab3.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

//EntityTransaction - used to control transactions on resource-local entity managers;
//EntityManager – used to interact with the persistence context (with the database session);

public abstract class AbstractDao<T> implements IDao<T> {
    @PersistenceContext
    //Persistence context -- The set of managed Entity objects in runtime
    private EntityManager entityManager;

    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract Class<T> getEntityClass();
//Transaction - a limited number of actions that either
//fully executed or rolled back.
    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public T findById(final Integer id) {
        T result = (T) entityManager.find(getEntityClass(), id);
        return result;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public T persist(final T object) {
        entityManager.persist(object);
        return object;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public void remove(final Integer id) {
        T result = (T) entityManager.find(getEntityClass(), id);
        entityManager.remove(result);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public T update(final T object) {
        T resultEntity = entityManager.merge(object);
        entityManager.persist(resultEntity);
        return resultEntity;
    }
}

//Persistence context is not directly available to developer
//
//There is no programmatic access to the Persistence context -
//It is not necessary
//
//Access to Persistence context is carried out through the
//EntityManager
//
//EntityManager type determines how the persistence context is
//created and deleted