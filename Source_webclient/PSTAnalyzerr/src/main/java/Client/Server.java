package Client;

import java.util.HashMap;
import java.util.Map;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

import Client.Controller.FileController;
import Client.Controller.MailController;
import Client.Controller.PageController;
import Client.Service.FileService;
import Client.Service.MailService;
import Client.Service.PageService;
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

        new PageController(new PageService());
        new FileController(new FileService());
        new MailController(new MailService());
    }
}



