package org.zk.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntitiesModelingIT {
    EntityManagerFactory entityManagerFactory;

    @Before
	public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.zk.jpa");
	}

    @After
	public void tearDown() throws Exception {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
	public void testSelectUsage() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        

        entityManager.getTransaction().commit();
        entityManager.close();
	}

    @Test
    public void testFindUsage() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        
        
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
