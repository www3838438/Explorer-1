package com.stratio.notebook.cassandra.doubles;

import com.datastax.driver.core.Session;
import com.stratio.notebook.gateways.Connector;

import java.util.Properties;

/**
 * Created by afidalgo on 15/10/15.
 */
public class MockSessionCassandra implements Connector<Session> {

    private Session session;

    public MockSessionCassandra(Session session){
        this.session = session;
    }


    @Override
    public Connector loadConfiguration(Properties properties) {
        return null;
    }

    @Override
    public Session getConnector() {
        return session;
    }
}
