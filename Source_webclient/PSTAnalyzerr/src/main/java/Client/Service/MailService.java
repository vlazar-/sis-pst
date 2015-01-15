package Client.Service;


import Client.JSONParser;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailService {

    Map mail = new HashMap<>();

    public Map index() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit = "{\n" +
                " \"size\": 5000, " +
                "   \"query\": {\n" +
                "      \"match_all\": {}\n" +
                "   }\n" +
                "}";

        Search search = new Search.Builder(upit).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("MAIL SERVICE:" + result.getJsonString());
            // mail.put("result", parseJson(result.getJsonString()));
            // return mail;
            return parseJson(result.getJsonString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map parseJson(String jsonString) {
            //JsonArray result = new JsonArray();
            JSONArray re = new JSONArray();

            JSONObject a = new JSONObject(jsonString);
            a = a.getJSONObject("hits");
            JSONArray b = a.getJSONArray("hits");


            for(int i=0; i<b.length(); i++){
                JSONObject t = b.getJSONObject(i);
                t = t.getJSONObject("_source");
                re.put(t);
            }

            JSONObject fr = new JSONObject();
            fr.put("mail", re);

            return JSONParser.jsonToMap(fr);

            /*JsonElement jsonElement = new JsonParser().parse(jsonString);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            jsonObject.getAsJsonObject("hits");     //top level
            jsonObject = jsonObject.getAsJsonObject("hits"); //level -1
            JsonArray jsonArray = jsonObject.getAsJsonArray("hits"); //level -2
            for (JsonElement r : jsonArray) {
                JsonObject temp = r.getAsJsonObject();
                temp = temp.getAsJsonObject("_source");
                //JsonObject mail = new JsonObject();
                //mail.add("mail", temp);
                result.add(temp);

            }

            JsonObject final_result = new JsonObject();
            final_result.add("mail", result);

            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject)jsonParser.parse(json);
            String ma = final_result.toString();
            JSONObject jo = new JSONObject(ma);*/
    }
}
