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

    public boolean CreateIndex(String indexName) {
        cirb = CreateNode.client.admin().indices().prepareCreate(indexName);//kreira index u kojem ce biti pohranjeni podaci, nešto poput baze podataka
        settings = new HashMap<>();
        //svi podaci ce se spremiti u samo jednom shard-u, u dokumentaciji od elasticsearcha je objasnjen shard;
        settings.put("number_of_shards", 1);
        //broj indexa koji odgovaraju upitu u clusteru
        settings.put("number_of_replicas", 1);
        cirb.setSettings(settings);
        try {
            createIndexResponse = cirb.execute().actionGet();
        } catch (IndexAlreadyExistsException e) {
            //index pod tim imenom vec postoji
            e.printStackTrace();
        }
        if (createIndexResponse != null && createIndexResponse.isAcknowledged()) {
            //index je uspjesno kreiran
            return true;
        } else {
            //index nije kreiran
            return false;
        }
    }

    public void DeleteIndex(String indexName) {
        final DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        final DeleteIndexResponse deleteIndexResponse = CreateNode.client.admin().indices().delete(deleteIndexRequest).actionGet();
        if (deleteIndexResponse.isAcknowledged()) {
            LOG.fine("Index " + indexName + " deleted");
        } else {
            LOG.warning("Index " + indexName + " not deleted");
        }
    }

    public boolean CheckIfIndexExists() {
        IndicesExistsResponse existsResponse = CreateNode.client.admin().indices().prepareExists("pstindex").execute().actionGet();
        if (existsResponse.isExists())
            return true;
        else
            return false;
    }

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
                System.out.println("MAPIRANJE JE NAPRAVLJENO");
            else
                System.out.println("MAPRIANJE NIJE NAPRAVLJENO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
