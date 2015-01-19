package Client.Controller;

import Client.Service.PageService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;

/**
 * Created by Viktor on 08/01/2015.
 */
public class PageController {

    public PageController(final PageService pageService){

        get("/", (request, response)-> new ModelAndView(null, "pages/index.mustache"), new MustacheTemplateEngine());

        get("/search", (request, response)-> new ModelAndView(null, "pages/search.mustache"), new MustacheTemplateEngine());

        get("/help", (request, response)-> new ModelAndView(pageService.help(), "pages/help.mustache"), new MustacheTemplateEngine());
    }
}
