package org.zk.env;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Creates an EntityManagerFactory.
 * <p>
 * Configuration of the persistence units is taken from <code>META-INF/persistence.xml</code>
 * and other sources. Additional <code>hbm.xml</code> file names can be given to the
 * constructor.
 * </p>
 */
public class JPASetup {

    protected final String persistenceUnitName;
    protected final EntityManagerFactory entityManagerFactory;

    public JPASetup(String persistenceUnitName) throws Exception {

        this.persistenceUnitName = persistenceUnitName;

        entityManagerFactory = Persistence.createEntityManagerFactory(getPersistenceUnitName());
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager createEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

}
