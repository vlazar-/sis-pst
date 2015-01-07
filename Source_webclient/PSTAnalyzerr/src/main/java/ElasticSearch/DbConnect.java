/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElasticSearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.codegen.Compiler.LOG;

import PST.PSTFileEmail;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.json.JSONArray;

/**
 *
 * @author goran
 */
public class DbConnect {

    CreateIndexRequestBuilder cirb;
    HashMap<String, Object> settings;
    CreateIndexResponse createIndexResponse = null;
    PSTFileEmail emailJson;

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

    public void FillIndex(JSONArray emailJSONArray) {
        //Map<String, Object> jsonDocument=new HashMap<String, Object>();
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject().value(emailJSONArray.getJSONObject(3)).endObject();//tu se nalazi jedan JSON objekt unutar JSONDokumenta
            //PutMappingResponse response = CreateNode.client.admin().indices().preparePutMapping("pstindex").setType("neki_tip_indeksa").setSource(builder).execute().actionGet();
            //jsonDocument.put(String.valueOf(i), builder);
            /*if (response.isAcknowledged()) {
                System.out.println("NAPRAVLJENO JE MAPIRANJE-PODACI MORAJU BITI UNUTRA");
            }*/
        } catch (IOException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
