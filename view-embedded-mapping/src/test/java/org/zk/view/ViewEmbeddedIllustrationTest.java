/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.zk.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class ViewEmbeddedIllustrationTest {
	//private SessionFactory sessionFactory;
    EntityManagerFactory entityManagerFactory;

    @Before
	public void setUp() throws Exception {
		// A SessionFactory is set up once for an application
//        sessionFactory = new Configuration()
//                .configure() // configures settings from hibernate.cfg.xml
//                .buildSessionFactory();
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
	}

    @After
	public void tearDown() throws Exception {
		//if ( sessionFactory != null ) {
		//	sessionFactory.close();
		//}
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
	@SuppressWarnings({ "unchecked" })
	public void testBasicUsage() {
		// create a couple of events...
		//Session session;
		//session.beginTransaction();

		// now lets pull events from the database and list them
		//Session session = session = sessionFactory.openSession();
        //session.beginTransaction();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //List<TestEntityAggregate> result = session.createQuery( "select e from TestEntityAggregate e" ).list();
        List<TestEntityAggregate> result = entityManager.createQuery( "select e from TestEntityAggregate e" ).getResultList();

        for (TestEntityAggregate tea : result) {
            System.out.println(tea);
        }
        //session.getTransaction().commit();
        //session.close();
        entityManager.getTransaction().commit();
        entityManager.close();
	}

    @Test
    public void testGetUsage() {
        //Session session;

        //session = sessionFactory.openSession();
        //session.beginTransaction();
        //TestEntityAggregate tea = (TestEntityAggregate) session.get(TestEntityAggregate.class, 1);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // Long needs to be sent as a id
        TestEntityAggregate tea = entityManager.find(TestEntityAggregate.class, 1L);

        System.out.print("test 2 ");
        System.out.println(tea);

        //session.getTransaction().commit();
        //session.close();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
