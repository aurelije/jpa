package org.zk.env;

import org.testng.annotations.*;

/**
 * Starts and stops the JPA environment before/after a test class.
 * <p>
 * Create a subclass to write unit tests. Access the <code>EntityManagerFactory</code>
 * with {@link JPAIntegrationTestBase#JPA} and create <code>EntityManager</code> instances.
 * </p>
 */
public class JPAIntegrationTestBase {

    // Static single database connection manager per test suite
    protected static TransactionManagerSetup TM;
    protected String persistenceUnitName;
    protected JPASetup JPA;

    @BeforeSuite()
    public void beforeSuite() throws Exception {
        TM = new TransactionManagerSetup();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        if (TM != null)
            TM.stop();
    }

    @BeforeClass
    public void beforeClass() throws Exception {
        configurePersistenceUnit();
    }

    public void configurePersistenceUnit() throws Exception {
        configurePersistenceUnit(null);
    }

    public void configurePersistenceUnit(String persistenceUnitName) throws Exception {
        this.persistenceUnitName = persistenceUnitName;
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        JPA = new JPASetup(persistenceUnitName);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        if (JPA != null) {
            JPA.getEntityManagerFactory().close();
        }
    }

}
