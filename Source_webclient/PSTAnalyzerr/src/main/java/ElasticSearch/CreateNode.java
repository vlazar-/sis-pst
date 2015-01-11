package ElasticSearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.params.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Singleton class for creating new instance of elasticsearch client.
 *
 * @author Matej
 */
public class CreateNode {
    private static CreateNode instance = null;
    public static Node node = null;
    public static Client client = null;

    /*
    Dummy constructor, use getInstance to create or get instance of CreateNode
    class
    */
    protected CreateNode() {
        //dummy constructor
    }

    /**
     * Method that creates new instance of returns existing
     */
    public static CreateNode getInstance() {
        if (instance == null) {
            instance = new CreateNode();
            node = nodeBuilder().node();
            client = node.client();

        }
        return instance;
    }

    /**
     * Elasticsearch node shutdown method
     */
    public static void nodeShutdown() {
        node.close();
    }

    public void GetEMailCountPerTime(String gt, String lt, String time) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit =
                "{\n" +
                        "   \"query\": {\n" +
                        "      \"filtered\": {\n" +
                        "         \"filter\": {\n" +
                        "            \"range\": {\n" +
                        "               \"msgDeliveryTime\": {\n" +
                        "                  \"gt\":" + "\"" + gt + "\"" + "," +
                        "                  \"lt\":" + "\"" + lt + "\"" +
                        "               }\n" +
                        "            }\n" +
                        "         }\n" +
                        "      }\n" +
                        "   },\n" +
                        "   \"aggs\": {\n" +
                        "      \"emails_per_day\": {\n" +
                        "         \"date_histogram\": {\n" +
                        "            \"field\": \"msgDeliveryTime\",\n" +
                        "            \"interval\":" + "\"" + time + "\"" + ",\n" +
                        "            \"format\": \"yyyy-MM-dd\",\n" +
                        "            \"min_doc_count\": 0,\n" +
                        "            \"extended_bounds\": {\n" +
                        "               \"min\": \"2014-01-01\",\n" +
                        "               \"max\": \"2014-12-31\"\n" +
                        "            }\n" +
                        "         }\n" +
                        "      }\n" +
                        "   }\n" +
                        "}";

        Search search = new Search.Builder(upit).setSearchType(SearchType.COUNT)
                .addIndex("pstindex")
                .build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("REZULTAT UPITAAAAAAAAAAAAAAA:" + result.getJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetEmailsPerSize() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetEmailsPerDataField(String param, String param2) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "           \"" + param + "\": \"" + param2 + "\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(upit).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("REZULTAT UPITAAAAAAAAAAAAAAA:" + result.getJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GetEmailCountPerSenderInbox() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit = "{\n" +
                "   \"aggs\": {\n" +
                "      \"sender\": {\n" +
                "         \"terms\": {\n" +
                "            \"field\": \"getSenderEmailAddress\"\n" +
                "         }\n" +
                "      }\n" +
                "   }\n" +
                "}";
        Search search = new Search.Builder(upit).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("REZULTAT UPITAAAAAAAAAAAAAAA:" + result.getJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GetEmailCountPerSenderSent() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200/")
                .multiThreaded(true)
                .build());
        JestClient clienttt = factory.getObject();
        String upit = "{\n" +
                "   \"aggs\": {\n" +
                "      \"sender\": {\n" +
                "         \"terms\": {\n" +
                "            \"field\": \"getDisplayTo\"\n" +
                "         }\n" +
                "      }\n" +
                "   }\n" +
                "}";
        Search search = new Search.Builder(upit).addIndex("pstindex").build();

        try {
            io.searchbox.core.SearchResult result = clienttt.execute(search);
            System.out.println("REZULTAT UPITAAAAAAAAAAAAAAA:" + result.getJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


