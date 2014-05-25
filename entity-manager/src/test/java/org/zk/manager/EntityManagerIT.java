package org.zk.manager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityManagerIT {
    EntityManagerFactory entityManagerFactory;

    @BeforeMethod
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.zk.jpa");
    }

    @AfterMethod
    public void tearDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }


}
