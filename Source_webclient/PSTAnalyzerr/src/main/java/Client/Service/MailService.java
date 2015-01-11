package Client.Service;


import Client.Model.Mail;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.params.SearchType;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viktor on 08/01/2015.
 */
public class MailService {

    public String index() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit = "{\n" +
                "   \"sort\": [\n" +
                "      {\n" +
                "         \"getMessageSize\": {\n" +
                "            \"order\": \"desc\"\n" +
                "         }\n" +
                "      }\n" +
                "   ],\n" +
                "   \"query\": {\n" +
                "      \"match_all\": {}\n" +
                "   }\n" +
                "}";

        Search search = new Search.Builder(upit).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("REZULTAT UPITAAAAAAAAAAAAAAA:" + result.getJsonString());
            return result.getJsonString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
