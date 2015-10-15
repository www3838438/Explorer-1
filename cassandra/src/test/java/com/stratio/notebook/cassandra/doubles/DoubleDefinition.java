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

package com.stratio.notebook.cassandra.doubles;


import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;


import com.datastax.driver.core.ColumnDefinitions;




import static org.powermock.api.support.membermodification.MemberModifier.stub;

/**
 * Created by afidalgo on 14/10/15.
 */
public class DoubleDefinition {


    public  ColumnDefinitions.Definition buildDefinitionWithName(String nameHeader) {
        ColumnDefinitions.Definition definition = mock(ColumnDefinitions.Definition.class);
        expect(definition.getName()).andReturn(nameHeader);
      //  replay(definition);
        return definition;
    }
}
