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

package com.stratio.notebook.cassandra.functions;


import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import com.stratio.notebook.cassandra.doubles.DoubleRow;
import com.stratio.notebook.cassandra.models.CellData;
import com.stratio.notebook.cassandra.models.RowData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afidalgo on 14/10/15.
 */
public class RowToRowDataFunctionTest {

    private List<String> header ;
    private final String header1 ="header1";
    private final String header2 ="header2";
    private DoubleRow doubleRow;

    @Before
    public void setUp(){
        header = new ArrayList<>();
        header.add(header1);
        header.add(header2);
        doubleRow = new DoubleRow(header1,header2);
    }


    @Test
    public void whenTransformRowReturnListCells(){

        RowToRowDataFunction function = new RowToRowDataFunction(header);
        RowData row = function.transform(doubleRow.bildRow());
        assertThat(row.cells().size(),is(2));

    }


    @Test
    public void whenTransformRowReturnCells(){

        RowToRowDataFunction function = new RowToRowDataFunction(header);
        RowData row = function.transform(doubleRow.bildRow());
        List<CellData> cells = row.cells();
        assertThat(cells.get(0).getValue(),is((Object)"header1"));
        assertThat(cells.get(1).getValue(),is((Object)"header2"));
    }
}
