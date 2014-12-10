/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.pstanalyzerr;

import com.pff.PSTFile;
import ElasticSearch.CreateNode;
import gui.MainForm;
import org.json.JSONArray;
import org.json.JSONObject;
import pstanalyzer.PSTFileEmail;


/**
 *
 * @author Matej
 */
public class main{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String fileName="D:"+"\\"+"FAKS"+"\\"+"4_CETVRTA_GODINA"+"\\"+"Sigurnost_informacijskih_sustava"+"\\"+"PST_dat"+"\\"+"goran_fazer@hotmail.com.pst";
        System.out.println(fileName);
        PSTFileEmail fileEmail=new PSTFileEmail();
        try{
            PSTFile pstFile=new PSTFile(fileName);
            fileEmail.proccessFolder(pstFile.getRootFolder());
        }catch (Exception e){
            System.out.println(e.toString());
        }
        CreateNode createNode = CreateNode.getInstance();
        //System.out.println("CLIENT DATA MOTHERFUCKER!!!!!: "+createNode.node.client().toString());
        JSONArray emailArray=fileEmail.GetEmailJsonArray();
        
        for(int i=0; i<emailArray.length(); i++){
            System.out.println("JSON OBJEKT: "+emailArray.getJSONObject(i).toString());
            
        }
        //System.out.println("VELICINA POLJA: "+emailArray.length());
        
        for(String i : fileEmail.GetFolderList()){
            System.out.println(i);
        }
        
        MainForm form = new MainForm();
        form.setVisible(true);
    }
    
}
