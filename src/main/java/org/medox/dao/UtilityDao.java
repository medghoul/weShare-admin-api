package org.medox.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UtilityDao {
    public static EntityManagerFactory factory;
    public static void initFactory(){
        factory= Persistence.createEntityManagerFactory("DefaultPersistenceUnit");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
    public static void shutdownFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
