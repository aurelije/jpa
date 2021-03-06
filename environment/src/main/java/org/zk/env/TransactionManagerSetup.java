package org.zk.env;

import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.util.logging.Logger;

/**
 * Provides a database connection pool with the Bitronix JTA transaction
 * manager (http://docs.codehaus.org/display/BTM/Home).
 * <p>
 * Hibernate will look up the datasource and <code>UserTransaction</code> through
 * JNDI, that's why you also need a <code>jndi.properties</code> file. A minimal
 * JNDI context is bundled with and started by Bitronix.
 * </p>
 */
public class TransactionManagerSetup {

    public static final String DATASOURCE_NAME = "myDS";
    public static final int DEFAULT_TRANSACTION_TIMEOUT = 180;
    private static final Logger logger =
            Logger.getLogger(TransactionManagerSetup.class.getName());
    protected final Context context = new InitialContext();
    protected final PoolingDataSource datasource;

    public TransactionManagerSetup() throws Exception {

        logger.fine("Starting database connection pool");

        logger.fine("Setting stable unique identifier for transaction recovery");
        TransactionManagerServices.getConfiguration().setServerId("myServer1234");

        logger.fine("Disabling JMX binding of manager in unit tests");
        TransactionManagerServices.getConfiguration().setDisableJmx(true);

        //logger.fine("Disabling transaction logging for unit tests");
        //TransactionManagerServices.getConfiguration().setJournal("null");

        logger.fine("Disabling warnings when the database isn't accessed in a transaction");
        TransactionManagerServices.getConfiguration().setWarnAboutZeroResourceTransaction(true);

        logger.fine("Setting timeout to " + DEFAULT_TRANSACTION_TIMEOUT);
        TransactionManagerServices.getConfiguration().setDefaultTransactionTimeout(DEFAULT_TRANSACTION_TIMEOUT);

        logger.fine("Creating connection pool");
        datasource = new PoolingDataSource();
        datasource.setUniqueName(DATASOURCE_NAME);
        datasource.setMinPoolSize(1);
        datasource.setMaxPoolSize(5);
        datasource.setPreparedStatementCacheSize(10);
        datasource.setIsolationLevel("READ_COMMITTED");
        datasource.setAllowLocalTransactions(true);
        datasource.setEnableJdbc4ConnectionTest(true);

        String dbEngine = System.getProperty("databaseProduct");
        DatabaseProduct databaseProduct = DatabaseProduct.valueOf(dbEngine.toUpperCase());

        logger.info("Setting up database connection: " + databaseProduct);
        databaseProduct.configuration.configure(datasource);

        logger.fine("Initializing transaction and resource management");
        datasource.init();
    }

    public Context getNamingContext() {
        return context;
    }

    public UserTransaction getUserTransaction() {
        try {
            return (UserTransaction) getNamingContext()
                    .lookup("java:comp/UserTransaction");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public DataSource getDataSource() {
        try {
            return (DataSource) getNamingContext().lookup(DATASOURCE_NAME);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void rollback() {
        UserTransaction tx = getUserTransaction();
        try {
            if (tx.getStatus() == Status.STATUS_ACTIVE ||
                    tx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
                tx.rollback();
            }
        } catch (Exception ex) {
            logger.severe("Rollback of transaction failed");
        }
    }

    public void stop() throws Exception {
        logger.fine("Stopping database connection pool");
        datasource.close();
        TransactionManagerServices.getTransactionManager().shutdown();
    }

}
