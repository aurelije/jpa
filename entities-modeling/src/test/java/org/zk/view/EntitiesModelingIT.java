package org.zk.view;

import org.fest.assertions.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zk.entities.Author;
import org.zk.entities.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class EntitiesModelingIT {
    EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.zk.jpa");
	}

    @After
    public void tearDown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    public void testPostSaveSuccessfulMinimal() {
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

        Long postId = post.getId();

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post foundPost = entityManager.find(Post.class, postId);

        Assertions.assertThat(post.equals(foundPost)).isTrue();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test(expected = PersistenceException.class)
    public void testPostSaveUnsuccessfulNoAuthor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post post = new Post();
        Author author = new Author();

        author.setUserName("testUser");
        author.setUserPassword("password");

        entityManager.persist(author);

        //post.setAuthor(author);
        post.setSubject("subject");
        post.setBody("This is a body\nof this blog post");

        entityManager.persist(post);

        entityManager.getTransaction().commit();
        entityManager.close();

        // results in :
        // javax.persistence.PersistenceException: org.hibernate.PropertyValueException: not-null property references a null or transient value : org.zk.entities.Post.author
    }

    @Test(expected = IllegalStateException.class)
    public void testPostSaveUnsuccessfulNoCascadeToAuthor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post post = new Post();
        Author author = new Author();

        author.setUserName("testUser");
        author.setUserPassword("password");

        //entityManager.persist(author);

        post.setAuthor(author);
        post.setSubject("subject");
        post.setBody("This is a body\nof this blog post");

        entityManager.persist(post);

        entityManager.getTransaction().commit();
        entityManager.close();

        // results in :
        // java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Not-null property references a transient value - transient instance must be saved before current operation : org.zk.entities.Post.author -> org.zk.entities.Author
    }

    @Test//(expected = PersistenceException.class)
    public void testPostSaveUnsuccessfulUserNameNotValid() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post post = new Post();
        Author author = new Author();

        author.setUserName("");
        author.setUserPassword("password");

        entityManager.persist(author);

        post.setAuthor(author);
        post.setSubject("subject");
        post.setBody("This is a body\nof this blog post");

        entityManager.persist(post);

        entityManager.getTransaction().commit();
        entityManager.close();

        // results in :

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
