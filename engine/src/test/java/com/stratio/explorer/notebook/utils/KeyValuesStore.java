/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.explorer.notebook.utils;


import org.apache.commons.lang.StringUtils;

public class KeyValuesStore {

    public static String [] VALUES_WITH_PARENTHESIS = new String [] {"first","(second)"};
    public static String [] LITS_VALUES = new String[]{"one","two"};


    public static final KeyValue FIRST_KEY_VALUE = new KeyValue("firstAnyKey","firstAnyValue");
    public static final KeyValue SECOND_KEY_VALUE = new KeyValue("secondAnyKey","secondAnyValue");
    public static final KeyValue KEY_WITH_PARENTESIS = new KeyValue(StringUtils.join(VALUES_WITH_PARENTHESIS, ""),"a");
    public static final KeyValue VALUE_WITH_NESTED_OBJECT = new KeyValue("ANYkEY","a:b,c:d");
    public static final KeyValue VALUE_WITH_LIST_VALUES  =new KeyValue(KeyValuesStore.FIRST_KEY_VALUE.key(),StringUtils.join(LITS_VALUES, ","));
}
