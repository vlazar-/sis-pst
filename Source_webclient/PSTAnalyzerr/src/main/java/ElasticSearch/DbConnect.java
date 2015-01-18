/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElasticSearch;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.indices.IndexAlreadyExistsException;

import java.util.HashMap;

import static jdk.nashorn.internal.codegen.Compiler.LOG;

/**
 * @author goran
 */
public class DbConnect {

    CreateIndexRequestBuilder cirb;
    HashMap<String, Object> settings;
    CreateIndexResponse createIndexResponse = null;

    /**
     * Creates index based on index name.
     *
     * @param indexName
     * @return
     */
    public boolean CreateIndex(String indexName) {
        //creates index with specific index name
        cirb = CreateNode.client.admin().indices().prepareCreate(indexName);
        settings = new HashMap<>();
        //all data stored in one shard
        //Specified in yml file inside resources folder
        settings.put("number_of_shards", 1);
        settings.put("number_of_replicas", 1);
        cirb.setSettings(settings);
        try {
            createIndexResponse = cirb.execute().actionGet();
        } catch (IndexAlreadyExistsException e) {
            //index already exists
            e.printStackTrace();
        }
        if (createIndexResponse != null && createIndexResponse.isAcknowledged()) {
            //index created
            return true;
        } else {
            //index not created
            return false;
        }
    }

    /**
     * Deletes index with specific index name.
     *
     * @param indexName
     */
    public void DeleteIndex(String indexName) {
        final DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        final DeleteIndexResponse deleteIndexResponse = CreateNode.client.admin().indices().delete(deleteIndexRequest).actionGet();
        if (deleteIndexResponse.isAcknowledged()) {
            LOG.fine("Index " + indexName + " deleted");
        } else {
            LOG.warning("Index " + indexName + " not deleted");
        }
    }

    /**
     * Checks if index already exists.
     *
     * @return
     */
    public boolean CheckIfIndexExists() {
        IndicesExistsResponse existsResponse = CreateNode.client.admin().indices().prepareExists("pstindex").execute().actionGet();
        if (existsResponse.isExists())
            return true;
        else
            return false;
    }

    /**
     * Mapping for created index.
     * Currently specifies that senderEmailAddress and To fields are not analyzed.
     */
    public void CreateMapping() {
        try {
            String mapiranje = "{\n" +
                    " \"email\": {\n" +
                    " \"properties\": {\n" +
                    " \"getSenderEmailAddress\": {\n" +
                    " \"type\": \"string\",\n" +
                    " \"index\": \"not_analyzed\"\n" +
                    " },\n" +
                    " \"getDisplayTo\": {\n" +
                    " \"type\": \"string\",\n" +
                    " \"index\": \"not_analyzed\"\n" +
                    " }\n" +
                    " }\n" +
                    " }\n" +
                    "}";

            PutMappingResponse response = CreateNode.client.admin().
                    indices().
                    preparePutMapping("pstindex").
                    setType("email").
                    setSource(mapiranje).
                    execute().
                    actionGet();
            if (response.isAcknowledged())
                System.out.println("MAPPING CREATED");
            else
                System.out.println("MAPPING NOT CREATED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
