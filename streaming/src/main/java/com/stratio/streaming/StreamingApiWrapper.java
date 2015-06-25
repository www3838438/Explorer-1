package com.stratio.streaming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stratio.streaming.api.StratioStreamingAPI;
import com.stratio.streaming.api.messaging.ColumnNameType;
import com.stratio.streaming.api.messaging.ColumnNameValue;
import com.stratio.streaming.commons.exceptions.StratioStreamingException;
import com.stratio.streaming.commons.messages.ColumnNameTypeValue;
import com.stratio.streaming.commons.streams.StratioStream;

/**
 * Created by idiaz on 24/06/15.
 */
public class StreamingApiWrapper {
    private StratioStreamingAPI api;

    public StreamingApiWrapper(StratioStreamingAPI api) {
        this.api = api;
    }

    /**
     * Action commands
     */

    public String indexStart(String stream) throws StratioStreamingException {
        api.indexStream(stream);
        return "Stream ".concat(stream).concat(" indexed correctly");
    }

    public String indexStop(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" unindexed correctly");
    }

    public String saveCassandraStart(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" attached to cassandra correctly");
    }

    public String saveCassandraStop(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" de-attached from cassandra correctly");
    }

    public String saveMongoStart(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" attached to mongo correctly");
    }

    public String saveMongoStop(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" de-attached from mongo correctly");
    }

    public String listenStart(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat(" listened correctly");
    }

    public String listenStop(String stream) throws StratioStreamingException {
        return "Stream ".concat(stream).concat("listener removed");
    }

    /**
     * Query commands
     */

    public String createQuery(String stream, String query) throws StratioStreamingException {
        String queryId = api.addQuery(stream, query);
        return "Query ".concat(stream).concat(" created correctly with id ".concat(queryId));
    }

    public String removeQuery(String stream, String id) throws StratioStreamingException {
        api.removeQuery(stream, id);
        return "Query ".concat(stream).concat(" dropped correctly with id ".concat(id));
    }

    /**
     * Stream commands
     */

    public String list() throws StratioStreamingException {
        List<StratioStream> list = api.listStreams();
        return StreamingUtils.listToString(list);

    }

    public String listQuerys(String stream) throws StratioStreamingException {
        List<ColumnNameTypeValue> columnsValues = api.columnsFromStream(stream);

        return StreamingUtils.listQueriesToString(columnsValues);
    }

    public String create(String stream, List<ColumnNameType> columns) throws StratioStreamingException {
        api.createStream(stream, columns);
        return "Stream ".concat(stream).concat(" created correctly");
    }

    public String drop(String stream) throws StratioStreamingException {
        api.dropStream(stream);
        return "Stream ".concat(stream).concat(" dropped correctly");

    }

    public String alter(String stream, List<ColumnNameType> columns) throws StratioStreamingException {
        api.alterStream(stream, columns);
        return "Stream ".concat(stream).concat(" altered correctly");

    }

    public String insert(String stream, List<ColumnNameValue> values) throws StratioStreamingException {
        api.insertData(stream, values);
        return "Added an event to stream ".concat(stream).concat(" correctly");

    }
}
