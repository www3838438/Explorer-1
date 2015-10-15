/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.stratio.notebook.cassandra.gateways;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.stratio.notebook.cassandra.doubles.DoubleSession;
import com.stratio.notebook.cassandra.models.CellData;
import com.stratio.notebook.cassandra.models.RowData;
import com.stratio.notebook.cassandra.models.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.swing.text.TableView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CassandraDriverTest  {



    private Cluster cluster;
    private Session session;
    private CassandraDriver driver;



    @Before
    public void setUp() throws InterruptedException, IOException {
        cluster = new Cluster.Builder().addContactPoints("10.100.0.82").withPort(9042).build();
        session = cluster.connect();
        session.execute("CREATE KEYSPACE myKeySpace WITH replication={'class' : 'SimpleStrategy', 'replication_factor':1}");
        session.execute("USE myKeySpace");
        session.execute("CREATE TABLE myTable(id varchar, value varchar, PRIMARY KEY(id));");
        session.execute("INSERT INTO myTable(id, value) values('myKey01','myValue01');");

        driver = new CassandraDriver(session);

    }

    @After
    public void tearDown(){
        session.execute("DROP KEYSPACE myKeySpace");
        session.close();
        cluster.close();
    }


    @Test
    public void headerWillBeRecovered() throws InterruptedException, IOException {

        Table result = driver.executeCommand("select * from mytable WHERE id='myKey01'");
        List<String> header = new ArrayList<>();
        header.add("id");
        header.add("value");
        assertThat(result.header(), is(header));
    }


    @Test
    public void rowsWillBerecovered(){
        Table result = driver.executeCommand("select * from mytable WHERE id='myKey01'");
        assertThat(result.rows().size(), is(1));
    }


    @Test
    public void cellsWitllBeRecovered(){
        Table result = driver.executeCommand("select * from mytable WHERE id='myKey01'");
        RowData rows = result.rows().get(0);
        List<CellData> cells = rows.cells();
        assertThat(result.rows().size(), is(2));

    }


}
