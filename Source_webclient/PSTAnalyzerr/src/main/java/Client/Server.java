package Client;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Created by Viktor on 15/12/2014.
 */
public class Server {

    public void initServer()
    {
        /*
        * IMPORTANT - static files location
        * */
        staticFileLocation("/public");

        get("/test", (request, response) -> "1,2,3, testing!");

        Map map = new HashMap();
        map.put("name", "Sam");

        get("/", (request, response)-> new ModelAndView(map, "pages/index.mustache"), new MustacheTemplateEngine());

    }
}



