package org.medox.dao;

import org.medox.model.Reservation;

import javax.persistence.EntityManager;

public class ReservationDao {
    private EntityManager entityManager;

    public ReservationDao() {
        entityManager = UtilityDao.getEntityManager();
    }

    public Reservation getReservation(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    public void saveReservation(Reservation reservation) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(reservation);
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
