package Client.Controller;

import static spark.Spark.*;
import Client.Service.MailService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailController {

    public MailController(final MailService mailService) {
        get("/mail", (request, response) -> new ModelAndView(mailService.index(), "pages/mail.mustache"), new MustacheTemplateEngine());

        get("/mail-raw", (request, response) -> mailService.index());
    }


}
