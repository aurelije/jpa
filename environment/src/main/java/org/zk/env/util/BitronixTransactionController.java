/**
 * Created by zkadragic on 6/25/14.
 */
package org.zk.env.util;

import org.eclipse.persistence.transaction.JTATransactionController;

import javax.naming.InitialContext;
import javax.transaction.TransactionManager;

public class BitronixTransactionController extends JTATransactionController {
    @Override
    protected TransactionManager acquireTransactionManager() throws Exception {
        return (TransactionManager) (new InitialContext())
                .lookup("java:comp/UserTransaction");
    }
}
