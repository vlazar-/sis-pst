/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElasticSearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder.*;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Singleton class for creating new instance of elasticsearch client.
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
    protected CreateNode(){
        //dummy constructor
    }
    /**
     * Method that creates new instance of returns existing
    */
    public static CreateNode getInstance(){
        if(instance == null){
            instance = new CreateNode();
            node = nodeBuilder().node();
            client = node.client();
            
        }
        return instance;
    }
    /**
     * Elasticsearch node shutdown method
     */
    public static void nodeShutdown(){
        node.close();
    }
    
    
    
    
}
