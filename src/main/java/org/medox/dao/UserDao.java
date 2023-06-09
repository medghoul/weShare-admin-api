package org.medox.dao;


import org.medox.model.User;

import javax.persistence.EntityManager;

public class UserDao {

    private EntityManager entityManager;

    public UserDao() {
        entityManager = UtilityDao.getEntityManager();
    }

    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    public void saveUser(User user) {
        try {

            if (user.getId() != null) {
                user = entityManager.merge(user);
            }

            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive() || entityManager.getTransaction().getRollbackOnly()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public User getUserByEmailPassword(String email, String password) {
        User user = entityManager.createNamedQuery("UserByEmailPassword", User.class)
                                 .setParameter("email", email)
                                 .setParameter("password", password)
                                 .getSingleResult();
        return user;
    }

    public User findUserByEmail(String email) {
        User user = entityManager.createNamedQuery("FindUserByEmail", User.class)
                                 .setParameter("email", email)
                                 .getSingleResult();
        return user;
    }

    public void close() {
        entityManager.close();
    }
}

