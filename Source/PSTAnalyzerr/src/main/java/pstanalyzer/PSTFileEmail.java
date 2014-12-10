/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pstanalyzer;

import com.pff.PSTException;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author goran
 */
public class PSTFileEmail {
    
    int depth=-1;
    JSONArray jsonArray=new JSONArray();
    List<String> folderList=new ArrayList<String>();
    
    public void proccessFolder(PSTFolder folder) throws PSTException, IOException{
        depth++;
        
        /**
         * Check if folder has subfolders, if it has call again 
         * method proccessFolder. This is simple recursion.
         */
        if(folder.hasSubfolders()){
            Vector<PSTFolder> childFolders=folder.getSubFolders();
            for(PSTFolder childFolder : childFolders){
                proccessFolder(childFolder);
                folderList.add(childFolder.getDisplayName()); //list of folder for each pst file
            }
        }
        
        /**
         * Goes through all email's in folder and takes data about email. 
         */
        if(folder.getContentCount()>0){
            depth++;
            PSTMessage email= (PSTMessage) folder.getNextChild(); 
            while(email!=null){
                
                JSONObject jsonEmailObject=new JSONObject();
                
                //jsonEmailObject.put("emailBody", email.getBody());
                
                //THEESE ARE NOT ALL ATTRIBUTES 
                if(email.getBody()!=null){
                    jsonEmailObject.put("emailBody", email.getBody());
                }else{
                    jsonEmailObject.put("emailBody", "null");
                }
                
                if(email.getBodyHTML()!=null){
                    jsonEmailObject.put("emailBodyHtml", email.getBodyHTML());
                }else{
                    jsonEmailObject.put("emailBodyHtml", "null");
                }
                
                if(email.getDisplayBCC()!=null){
                    jsonEmailObject.put("displayBcc", email.getDisplayBCC());
                }else{
                    jsonEmailObject.put("displayBcc", "null");
                }
                
                if(email.getDisplayCC()!=null){
                    jsonEmailObject.put("displayCC", email.getDisplayCC());
                }else{
                    jsonEmailObject.put("displayCC", "null");
                }
                
                if(email.getMessageDeliveryTime()!=null){
                    jsonEmailObject.put("msgDeliveryTime", email.getMessageDeliveryTime());
                }else{
                    jsonEmailObject.put("msgDeliveryTime", "null");
                }
                
                if(String.valueOf(email.getNumberOfAttachments())!=null){
                    jsonEmailObject.put("numberOfAttachments", email.getNumberOfAttachments());
                }else{
                    jsonEmailObject.put("numberOfAttachments", "null");
                }
                
                if(String.valueOf(email.getNumberOfRecipients())!=null){
                    jsonEmailObject.put("numberOfRecipients", email.getNumberOfRecipients());
                }else{
                    jsonEmailObject.put("numberOfRecipients", "null");
                }
                
                if(email.getReceivedByAddress()!=null){
                    jsonEmailObject.put("recievedAdress", email.getReceivedByAddress());
                }else{
                    jsonEmailObject.put("recievedAdress", "null");
                }
                
                if(email.getSenderName()!=null){
                    jsonEmailObject.put("senderEmail", email.getSenderName());
                }else{
                    jsonEmailObject.put("senderEmail", "null");
                }
                //THEESE ARE NOT ALL ATTRIBUTES 
                
                //jsonArray.add(jsonEmailObject);
                //System.out.println(jsonEmailObject.toJSONString());
                email=(PSTMessage) folder.getNextChild(); //get's next email in folder
            }
            depth--;
        }
        depth--;
    }
    
    /**
     * Method that returns list of email JSON objects
     * @return 
     */
    public JSONArray GetEmailJsonArray(){
            return jsonArray;
    }
    
    /**
     * Method that return list of folder names. It assumed to be used on form 
     * where will be displayed list of all folders and subfolder.
     * @return 
     */
    public List<String> GetFolderList(){
        return folderList;
    }
}
