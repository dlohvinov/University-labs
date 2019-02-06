package ua.lviv.iot.lab3.persistence.dao;


import ua.lviv.iot.lab3.model.EngeneeringEquipment;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@Dependent
public class EngeneeringEquipmentDaoImpl extends AbstractDao<EngeneeringEquipment>
        implements EngeneeringEquipmentDao, Serializable {

    private static final long serialVerisionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Class<EngeneeringEquipment> getEntityClass() {
        return EngeneeringEquipment.class;
    }

}
