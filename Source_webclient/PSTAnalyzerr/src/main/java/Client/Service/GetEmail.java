package Client.Service;

import Client.JSONParser;
import Client.Service.MailService;
import ElasticSearch.CreateNode;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by matha on 18.01.15..
 */
public class GetEmail {

    Map email = new HashMap<>();

    public GetEmail() {
        //get("/API/v1/GetEmail", (request, response) -> new ModelAndView(mailService.index(), "pages/mail.mustache"), new MustacheTemplateEngine());

        //get("/API/v1/GetEmail", (request, response) -> this.getEmail(request.params(":id")));
    }

    public String getEmail(String index){

        try {
            Client client = CreateNode.client;
            GetResponse response = client.prepareGet("pstindex", "email", index)
                    .execute()
                    .actionGet();
            return response.getSourceAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmailKeyWords(String word){

        /*try {
            Client client = CreateNode.client;
            GetResponse response = client.prepareGet("pstindex", "email", index)
                    .execute()
                    .actionGet();
            return response.getSourceAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
