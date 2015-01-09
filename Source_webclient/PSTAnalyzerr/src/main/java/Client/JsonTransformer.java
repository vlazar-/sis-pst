package Client;

import com.google.gson.Gson;
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

    /**
     * Transform returned object of HTTP response to Json object
     * @return
     */
    public static ResponseTransformer json(){
        return JsonTransformer::makeJson;
    }
}
