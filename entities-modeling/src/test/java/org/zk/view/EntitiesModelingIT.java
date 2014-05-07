package org.zk.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zk.entites.Author;
import org.zk.entites.Post;

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
    public void testPostSaveSuccessful() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post post = new Post();
        Author author = new Author();

        author.setUserName("testUser");
        author.setUserPassword("password");

        entityManager.persist(author);

        post.setAuthor(author);
        post.setSubject("subject");
        post.setBody("This is a body\nof this blog post");

        entityManager.persist(post);

        entityManager.getTransaction().commit();
        entityManager.close();
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
