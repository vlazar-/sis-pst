package Client.Service;


import Client.JSONParser;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailService {

    Map mail = new HashMap<>();

    /**
     * Returns all emails from PST index.
     *
     * @return
     */
    public Map index() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient jestClient = factory.getObject();
        String query = "{\n" +
                " \"size\": 5000, " +
                "   \"query\": {\n" +
                "      \"match_all\": {}\n" +
                "   }\n" +
                "}";

        Search search = new Search.Builder(query).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = jestClient.execute(search);
            System.out.println("MAIL SERVICE - index:" + result.getJsonString());
            // mail.put("result", parseJson(result.getJsonString()));
            // return mail;
            return parseResponse(result.getJsonString(), "mail");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns list of folders from PST indeks.
     *
     * @param folder
     * @return
     */
    public Map getFolder(String folder) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient jestClient = factory.getObject();
        String query = "{\n" +
                " \"size\": 5000, " +
                "   \"query\": {\n" +
                "       \"query_string\": {" +
                "           \"query\": \"folderName:" + folder + "\" \n" +
                "        }\n" +
                "   }\n" +
                "}";

        Search search = new Search.Builder(query).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = jestClient.execute(search);
            System.out.println("MAIL SERVICE -folder( " + folder + " ):" + result.getJsonString());
            // mail.put("result", parseJson(result.getJsonString()));
            // return mail;
            return parseResponse(result.getJsonString(), "mail");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Returns email for single id.
     *
     * @param id email id.
     * @return JSON email object.
     */
    public Map getSingle(String id) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient jestClient = factory.getObject();
        String query = "{ \n" +
                "\"size\": 1, \n" +
                "\"query\": { \n" +
                "    \"query_string\": { \n" +
                "       \"query\": \n" +
                "           \"internetMessageId:" + id + "\" \n" +
                "        }\n" +
                "   }\n" +
                "}";

        Search search = new Search.Builder(query).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = jestClient.execute(search);
            System.out.println("MAIL SERVICE - single( " + id + " ):" + result.getJsonString());
            // mail.put("result", parseJson(result.getJsonString()));
            // return mail;
            return parseResponse(result.getJsonString(), "single");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Parse response from pst index. Remove meta data.
     *
     * @param jsonString
     * @param responseName
     * @return
     */
    public Map parseResponse(String jsonString, String responseName) {
        //JsonArray result = new JsonArray();
        JSONArray re = new JSONArray();

        JSONObject a = new JSONObject(jsonString);
        a = a.getJSONObject("hits");
        JSONArray b = a.getJSONArray("hits");


        for (int i = 0; i < b.length(); i++) {
            JSONObject t = b.getJSONObject(i);
            t = t.getJSONObject("_source");
            re.put(t);
        }

        JSONObject fr = new JSONObject();
        fr.put(responseName, re);

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
