package org.medox.dao;


import org.medox.model.Review;

import javax.persistence.EntityManager;


public class ReviewDao {

    private EntityManager entityManager;

    public ReviewDao() {
        entityManager = UtilityDao.getEntityManager();
    }

    public Review getReview(Long id) {
        return entityManager.find(Review.class, id);
    }

    public void saveReview(Review review) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(review);
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
