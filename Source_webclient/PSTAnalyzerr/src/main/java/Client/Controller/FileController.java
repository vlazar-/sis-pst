package Client.Controller;

import Client.Service.FileService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static Client.JsonTransformer.objectToJson;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Viktor on 08/01/2015.
 */
public class FileController {

    public FileController(final FileService fileService){

        get("/pst-open", (request, response)
                -> new ModelAndView(null, "pages/pst-open.mustache"), new MustacheTemplateEngine());

        post("/pst-path", (request, response)
                -> fileService.getFile(
                request.queryParams("fileName")
                ), objectToJson());
    }
}
