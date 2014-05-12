package org.zk.entities;

import org.fest.assertions.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;


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
    public void postSaveSuccessfulMinimal() {
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

    @Test
    public void postSaveSuccessfulAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Post post = new Post();
        Author author = new Author();

        author.setUserName("testUser");
        author.setUserPassword("password");

        entityManager.persist(author);

        PostComment comment = new PostComment();
        comment.setAuthor(author);
        comment.setContent("This is a post");

        post.addComment(comment); // no need to persist comment, cascade will do the trick!

        Tag testTag = new Tag();
        testTag.setName("Test"); // no need to persist tag, cascade will do the trick!

        post.addTag(testTag);

        Image image1 = new Image("image title1", "illustration1.jpg", 200, 300);
        Image image2 = new Image("image title2", "illustration2.jpg", 100, 80);

        post.addImage(image1);
        post.addImage(image2);

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
    public void postSaveUnsuccessfulNoAuthor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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

            // results in :
            // javax.persistence.PersistenceException: org.hibernate.PropertyValueException: not-null property references a null or transient value : org.zk.entities.Post.author

        } catch (Exception e) {
            if (entityManager.isOpen() && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Test(expected = IllegalStateException.class)
    public void postSaveUnsuccessfulNoCascadeToAuthor() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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

            // results in :
            // java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Not-null property references a transient value - transient instance must be saved before current operation : org.zk.entities.Post.author -> org.zk.entities.Author
        } catch (Exception e) {
            if (entityManager.isOpen() && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
        }
    }

    @Test(expected = RollbackException.class)
    public void postSaveUnsuccessfulUserNameNotValid() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("foo boo");
            author.setUserPassword("password");

            entityManager.persist(author);

            post.setAuthor(author);
            post.setSubject("subject");
            post.setBody("This is a body\nof this blog post");

            entityManager.persist(post);

            entityManager.getTransaction().commit();
            entityManager.close();

            // results in :
            // javax.persistence.RollbackException: Error while committing the Transaction
            // ...
            // Caused by: javax.validation.ConstraintViolationException: Validation failed for classes [org.zk.entities.Author] during persist time for groups [javax.validation.groups.Default, ]
            //     List of constraint violations:[
            //         ConstraintViolationImpl{interpolatedMessage='must match "^\w{3,20}$"', propertyPath=userName, rootBeanClass=class org.zk.entities.Author, messageTemplate='{javax.validation.constraints.Pattern.message}'}
            //     ]

        } catch (Exception e) {
            if (entityManager.isOpen() && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

}
