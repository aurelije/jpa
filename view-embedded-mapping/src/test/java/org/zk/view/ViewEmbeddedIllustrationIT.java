package org.zk.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class ViewEmbeddedIllustrationIT {
    EntityManagerFactory entityManagerFactory;

    @Before
	public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
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

        List<TestEntityAggregate> result = entityManager.createQuery(
        		"select e from TestEntityAggregate e", TestEntityAggregate.class).getResultList();
        
        assertThat(result).hasSize(4);
        assertThat(extractProperty("id").from(result)).containsOnly(-1L, -2L, -3L, -4L);

        System.out.println("testSelectUsage");
        for (TestEntityAggregate tea : result) {
            System.out.println(tea);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
	}

    @Test
    public void testFindUsage() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // Long needs to be sent as a id
        TestEntityAggregate tea = entityManager.find(TestEntityAggregate.class, -1L);
        
        assertThat(tea).isNotNull();
        assertThat(tea.getId()).isEqualTo(-1L);

        System.out.println("testFindUsage");
        System.out.println(tea);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
