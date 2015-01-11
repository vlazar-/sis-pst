package Client.Controller;

import static Client.JsonTransformer.objectToJson;
import static spark.Spark.*;
import Client.Service.MailService;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailController {

    public MailController(final MailService mailService){
        get("/mail", (req, res) -> mailService.index());
    }
}
