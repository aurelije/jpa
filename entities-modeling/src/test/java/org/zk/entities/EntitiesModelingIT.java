package org.zk.entities;

import org.fest.assertions.api.Assertions;
import org.testng.annotations.Test;
import org.zk.env.JPAIntegrationTestBase;

import javax.persistence.EntityManager;
import javax.transaction.RollbackException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;


public class EntitiesModelingIT extends JPAIntegrationTestBase {

    @Override
    public void configurePersistenceUnit() throws Exception {
        configurePersistenceUnit("org.zk.jpa");
    }

    @Test
    public void postSaveSuccessfulMinimal() throws Exception {
        UserTransaction tx = TM.getUserTransaction();

        try {
            tx.begin(); // JTA UserTransaction
            EntityManager em = JPA.createEntityManager();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("testUser");
            author.setUserPassword("password");

            em.persist(author);

            post.setAuthor(author);
            post.setSubject("subject");
            post.setBody("This is a body\nof this blog post");

            em.persist(post);

            Long postId = post.getId();

            tx.commit();
            em.close();

            em = JPA.createEntityManager();
            tx.begin();

            Post foundPost = em.find(Post.class, postId);

            Assertions.assertThat(post.equals(foundPost)).isTrue();

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }

    @Test
    public void postSaveSuccessfulAll() throws Exception {
        UserTransaction tx = TM.getUserTransaction();

        try {
            tx.begin(); // JTA UserTransaction
            EntityManager em = JPA.createEntityManager();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("testUser");
            author.setUserPassword("password");

            em.persist(author);

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

            em.persist(post);

            Long postId = post.getId();

            tx.commit();
            em.close();

            em = JPA.createEntityManager();
            tx.begin();

            Post foundPost = em.find(Post.class, postId);

            Assertions.assertThat(post.equals(foundPost)).isTrue();

            tx.commit();
            em.close();
        } finally {
            TM.rollback();
        }
    }

    // Stupid Hibernate throws RollbackException and disobeys JPA specification
    // the bug is reported but they won't fix it: https://hibernate.atlassian.net/browse/HHH-8028
    @Test(expectedExceptions = {ConstraintViolationException.class, RollbackException.class})
    public void postSaveUnsuccessfulNoAuthor() throws Exception {
        UserTransaction tx = TM.getUserTransaction();

        try {
            tx.begin(); // JTA UserTransaction
            EntityManager em = JPA.createEntityManager();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("testUser");
            author.setUserPassword("password");

            em.persist(author);

            //post.setAuthor(author);
            post.setSubject("subject");
            post.setBody("This is a body\nof this blog post");

            em.persist(post);

            tx.commit();
            em.close();

            // results in :
            // javax.persistence.PersistenceException: org.hibernate.PropertyValueException: not-null property references a null or transient value : org.zk.entities.Post.author

        } catch (Exception e) {

            /*try {
                if (entityManager.isOpen() && entityManager.getTransaction().isActive() && entityManager.getTransaction().getRollbackOnly()) {
                    entityManager.getTransaction().rollback();
                }
            } catch (Exception rbEx) {
                System.err.println("Rollback of transaction failed, trace follows!");
                rbEx.printStackTrace(System.err);
            }*/
            throw e;
        } finally {
            TM.rollback();
        }
    }

    // Stupid Hibernate throws IllegalStateException but should wrap that into RollbackException as EclipseLink does
    @Test(expectedExceptions = {RollbackException.class, IllegalStateException.class})
    public void postSaveUnsuccessfulNoCascadeToAuthor() throws Exception {
        UserTransaction tx = TM.getUserTransaction();

        try {
            tx.begin(); // JTA UserTransaction
            EntityManager em = JPA.createEntityManager();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("testUser");
            author.setUserPassword("password");

            //entityManager.persist(author);

            post.setAuthor(author);
            post.setSubject("subject");
            post.setBody("This is a body\nof this blog post");

            em.persist(post);

            tx.commit();
            em.close();

            // results in :
            // java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Not-null property references a transient value - transient instance must be saved before current operation : org.zk.entities.Post.author -> org.zk.entities.Author
        } catch (Exception e) {
            System.err.println("EntitiesModelingIT.postSaveUnsuccessfulNoCascadeToAuthor");
            System.err.println("e.toString() + \"\n \" + e.getMessage() = " + e.toString() + " \n" + e.getMessage());
            /*try {
                if (entityManager.isOpen() && entityManager.getTransaction().isActive() && entityManager.getTransaction().getRollbackOnly()) {
                    entityManager.getTransaction().rollback();
                }
            } catch (Exception rbEx) {
                System.err.println("Rollback of transaction failed, trace follows!");
                rbEx.printStackTrace(System.err);
            }*/
            throw e;
        } finally {
            /*if (entityManager.isOpen()) {
                entityManager.close();
            }*/
            TM.rollback();
        }
    }

    @Test(expectedExceptions = {ConstraintViolationException.class, RollbackException.class})
    public void postSaveUnsuccessfulUserNameNotValid() throws Exception {
        UserTransaction tx = TM.getUserTransaction();

        try {
            tx.begin(); // JTA UserTransaction
            EntityManager em = JPA.createEntityManager();

            Post post = new Post();
            Author author = new Author();

            author.setUserName("foo boo");
            author.setUserPassword("password");

            em.persist(author);

            post.setAuthor(author);
            post.setSubject("subject");
            post.setBody("This is a body\nof this blog post");

            em.persist(post);

            tx.commit();
            em.close();

            // results in :
            // javax.persistence.RollbackException: Error while committing the Transaction
            // ...
            // Caused by: javax.validation.ConstraintViolationException: Validation failed for classes [org.zk.entities.Author] during persist time for groups [javax.validation.groups.Default, ]
            //     List of constraint violations:[
            //         ConstraintViolationImpl{interpolatedMessage='must match "^\w{3,20}$"', propertyPath=userName, rootBeanClass=class org.zk.entities.Author, messageTemplate='{javax.validation.constraints.Pattern.message}'}
            //     ]

        } catch (Exception e) {
            System.err.println("EntitiesModelingIT.postSaveUnsuccessfulUserNameNotValid");
            System.err.println("e.toString() + \"\n \" + e.getMessage() = " + e.toString() + " \n" + e.getMessage());
            /*try {
                if (entityManager.isOpen() && entityManager.getTransaction().isActive() && entityManager.getTransaction().getRollbackOnly()) {
                    entityManager.getTransaction().rollback();
                }
            } catch (Exception rbEx) {
                System.err.println("Rollback of transaction failed, trace follows!");
                rbEx.printStackTrace(System.err);
            }*/
            throw e;
        } finally {
            /*if (entityManager.isOpen()) {
                entityManager.close();
            }*/
            TM.rollback();
        }

    }

}
