
package ElasticSearch;

import java.util.HashMap;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.indices.IndexAlreadyExistsException;

/**
 *
 * @author goran
 */
public class DbConnect {

    CreateIndexRequestBuilder cirb;
    HashMap<String,Object> settings;
    CreateIndexResponse createIndexResponse=null;

    public boolean createIndex(){

        cirb=CreateNode.client.admin().indices().prepareCreate("pstindex");//kreira index u kojem ce biti pohranjeni podaci, nešto poput baze podataka
        settings=new HashMap<>();

        //svi podaci ce se spremiti u samo jednom shard-u, u dokumentaciji od elasticsearcha je objasnjen shard;
        settings.put("number_of_shards", 1);

        //broj indexa koji odgovaraju upitu u clusteru
        settings.put("number_of_replicas", 1);

        cirb.setSettings(settings);

        try{
            createIndexResponse=cirb.execute().actionGet();
        }catch(IndexAlreadyExistsException e){
            //index pod tim imenom vec postoji
            e.printStackTrace();
        }

        if(createIndexResponse !=null && createIndexResponse.isAcknowledged()){
            //index je uspjesno kreiran
            return true;
        }else{
            //index nije kreiran
            return false;
        }
    }
}
