package org.medox.dao;



import org.medox.model.Trip;

import javax.persistence.EntityManager;
import java.util.List;

public class TripDao {

    private EntityManager entityManager;

    public TripDao() {
        entityManager = UtilityDao.getEntityManager();
    }

    public Trip getTrip(Long id) {
        return entityManager.find(Trip.class, id);
    }

    public void saveTrip(Trip trip) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(trip);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive() || entityManager.getTransaction().getRollbackOnly()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void close() {
        entityManager.close();
    }
}