package Client;

import com.google.gson.*;
import spark.ResponseTransformer;

/**
 * Transform object to JSON.
 *
 * Created by Viktor on 08/01/2015.
 */
public class JsonTransformer {

    public static String makeJson(Object object){
        return new Gson().toJson(object);
    }

    public static String makeJestJson(io.searchbox.core.SearchResult jest){
        return new Gson().toJson(jest);
    }

    /**
     * Transform returned object to Json
     * @return
     */
    public static ResponseTransformer objectToJson(){
        return JsonTransformer::makeJson;
    }

    /**
     * Transform returned Jest object to Json
     * @return
     */
   // public static ResponseTransformer jestToJson(){ return JsonTransformer::makeJestJson; }

    /*public JsonArray parseJson(String jsonString){

        JsonElement jsonElement = new JsonParser().parse(jsonString);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        jsonObject.getAsJsonObject("hits");
        JsonArray jsonArray = jsonObject.getAsJsonArray("hits");
        return jsonArray;
    }/*/

}
