package Client.Service;


import Client.Model.Mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailService {

    private Map<String, Mail> users = new HashMap<>();

    public List<Mail> index() {
        return new ArrayList<>(users.values());
    }

    /**
     * TO DO
     *
     * get data from elasticsearch
     */

}
