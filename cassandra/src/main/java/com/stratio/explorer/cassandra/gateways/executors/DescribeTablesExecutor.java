package com.stratio.explorer.cassandra.gateways.executors;

import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.TableMetadata;
import com.stratio.explorer.cassandra.functions.CellDataToHeader;
import com.stratio.explorer.cassandra.functions.KeyspaceTablestoRowData;
import com.stratio.explorer.cassandra.models.CellData;
import com.stratio.explorer.cassandra.models.RowData;
import com.stratio.explorer.cassandra.models.Table;
import com.stratio.explorer.cassandra.utils.ListUtils;
import com.stratio.explorer.functions.TransformFunction;
import com.stratio.explorer.lists.FunctionalList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Execute DESCRIBE TABLES .
 */
public class DescribeTablesExecutor implements DescribeExecutor{


    public static final String NAME_KEYSPACE = "Name KeySpace";
    public static final String NAME_TABLE ="Name Table";

    public static final String WORD_SELECTOR ="TABLES";


    /**
     * Is not mandatory
     * @param param
     */
    @Override
    public void optionalParam(String param) {
        //lefty empty deliverely
    }


    /**
     * Execute DESCRIBE TABLES .
     * @param metaData
     * @return table
     */
    @Override
    public Table execute(Metadata metaData) {
        FunctionalList<KeyspaceMetadata,RowData> functional = new FunctionalList<>( metaData.getKeyspaces());
        List<RowData> rows = functional.map(new KeyspaceTablestoRowData());
        return new Table(buildHeader(rows.get(0)), rows);
    }

    private List<String> buildHeader(RowData row){
        List<String> header = new ArrayList<>();
        if (row!=null){
            FunctionalList<CellData,String> functional = new FunctionalList<>(row.cells());
            header = functional.map(new CellDataToHeader(NAME_KEYSPACE,NAME_TABLE));
        }
        return header;
    }
}
