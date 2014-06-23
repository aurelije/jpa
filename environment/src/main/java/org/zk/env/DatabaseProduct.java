package org.zk.env;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public enum DatabaseProduct {

    H2(
            new DataSourceConfiguration() {
                @Override
                public void configure(PoolingDataSource ds) {
                    ds.setClassName("org.h2.jdbcx.JdbcDataSource");

                    // External instance: jdbc:h2:tcp://localhost/mem:test;USER=sa
                    ds.getDriverProperties().put("URL", "jdbc:h2:~/test;MODE=PostgreSQL");

                    // TODO: http://code.google.com/p/h2database/issues/detail?id=502
                    ds.getDriverProperties().put("user", "sa");
                }
            }
    ),

    POSTGRESQL(
            new DataSourceConfiguration() {
                @Override
                public void configure(PoolingDataSource ds) {
                    ds.setClassName("org.postgresql.xa.PGXADataSource");
                    ds.getDriverProperties().put("serverName", "127.0.0.1");
                    ds.getDriverProperties().put("databaseName", "test");
                    ds.getDriverProperties().put("user", "nonadmin");
                    ds.getDriverProperties().put("password", "nonadmin");
                }
            }
    ),;

    public DataSourceConfiguration configuration;

    private DatabaseProduct(DataSourceConfiguration configuration) {
        this.configuration = configuration;
    }

    public interface DataSourceConfiguration {

        void configure(PoolingDataSource ds);
    }

}
