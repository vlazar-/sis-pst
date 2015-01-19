package Client.Controller;

import static spark.Spark.*;

import Client.Service.GetEmail;
import Client.Service.MailService;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailController {

    public MailController(final MailService mailService) {
        /**
         * Get mail from .pst
         */
        get("/mail", (request, response) -> new ModelAndView(mailService.index(), "pages/mail.mustache"), new MustacheTemplateEngine());
        get("/mail-raw", (request, response) -> mailService.index());
        /**
         * Get mail from INBOX folder
         */
        get("/mail-inbox", (request, response) -> new ModelAndView(mailService.getFolder("Inbox"), "pages/mail.mustache"), new MustacheTemplateEngine());
        get("/mail-inbox-raw", (request, response) -> mailService.getFolder("Inbox"));
        /**
         * Get mail from SENT folder
         */
        get("/mail-sent", (request, response) -> new ModelAndView(mailService.getFolder("Sent"), "pages/mail.mustache"), new MustacheTemplateEngine());
        get("/mail-sent-raw", (request, response) -> mailService.getFolder("Sent"));
        /**
         * Get mail from TRASH folder
         */
        get("/mail-trash", (request, response) -> new ModelAndView(mailService.getFolder("Trash"), "pages/mail.mustache"), new MustacheTemplateEngine());
        get("/mail-trash-raw", (request, response) -> mailService.getFolder("Trash"));

        //get("/mail-single/:id", (request, response) -> mailService.getSingle(request.params(":id")));
        get("/mail-single/:id", (request, response) -> new ModelAndView(mailService.getSingle(request.params(":id")), "pages/mail-single.mustache"), new MustacheTemplateEngine());
        /**
         * API queries.
         */
        get("/API/v1/getEmail/:id", (request, response) -> new GetEmail().getEmail(request.params(":id")));
        get("/API/v1/getEmailFromKeyword/:keyword", (request, response) -> new GetEmail().getEmail(request.params(":id")));
    }


}
