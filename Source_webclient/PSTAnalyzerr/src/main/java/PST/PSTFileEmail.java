package PST;

import ElasticSearch.CreateNode;
import com.pff.PSTException;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

import java.io.IOException;
import java.util.*;


/**
 * @author goran
 */
public class PSTFileEmail {

    int depth = -1;
    int index=0;
    Map<String, Object> jsonDocument=new HashMap<String, Object>();
    List<String> folderList = new ArrayList<String>();

    public void proccessFolder(PSTFolder folder) throws PSTException, IOException {
        depth++;

        /**
         * Check if folder has subfolders, if it has call again
         * method proccessFolder. This is simple recursion.
         */
        if (folder.hasSubfolders()) {
            Vector<PSTFolder> childFolders = folder.getSubFolders();
            for (PSTFolder childFolder : childFolders) {
                proccessFolder(childFolder);
                folderList.add(childFolder.getDisplayName()); //list of folder for each pst file
            }
        }

        /**
         * Goes through all email's in folder and takes data about email.
         */
        if (folder.getContentCount() > 0) {
            depth++;
            PSTMessage email = (PSTMessage) folder.getNextChild();
            while (email != null) {
                index++;

                //THESE ARE NOT ALL ATTRIBUTES
                if (email.getBody() != null) {
                    //jsonDocument.put("emailBody", email.getBody());
                } else {
                    jsonDocument.put("emailBody", "null");
                }

                if (email.getBodyHTML() != null) {
                    //jsonDocument.put("emailBodyHtml", email.getBodyHTML());
                } else {
                    jsonDocument.put("emailBodyHtml", "null");
                }

                if (email.getDisplayBCC() != null) {
                    jsonDocument.put("displayBcc", email.getDisplayBCC());
                } else {
                    jsonDocument.put("displayBcc", "null");
                }

                if (email.getDisplayCC() != null) {
                    jsonDocument.put("displayCC", email.getDisplayCC());
                } else {
                    jsonDocument.put("displayCC", "null");
                }

                if (email.getMessageDeliveryTime() != null) {
                    jsonDocument.put("msgDeliveryTime", email.getMessageDeliveryTime());
                } else {
                    jsonDocument.put("msgDeliveryTime", "null");
                }

                if (String.valueOf(email.getNumberOfAttachments()) != null) {
                    jsonDocument.put("numberOfAttachments", email.getNumberOfAttachments());
                } else {
                    jsonDocument.put("numberOfAttachments", "null");
                }

                if (String.valueOf(email.getNumberOfRecipients()) != null) {
                    jsonDocument.put("numberOfRecipients", email.getNumberOfRecipients());
                } else {
                    jsonDocument.put("numberOfRecipients", "null");
                }

                if (email.getReceivedByAddress() != null) {
                    jsonDocument.put("recievedAdress", email.getReceivedByAddress());
                } else {
                    jsonDocument.put("recievedAdress", "null");
                }

                if (email.getSenderName() != null) {
                    jsonDocument.put("senderEmail", email.getSenderName());
                } else {
                    jsonDocument.put("senderEmail", "null");
                }
                //THESE ARE NOT ALL ATTRIBUTES

                System.out.println("BROJ INDEXA: "+String.valueOf(index));
                CreateNode.client.prepareIndex("pstindex", "email", String.valueOf(index)).setSource(jsonDocument).execute().actionGet();
                email = (PSTMessage) folder.getNextChild(); //get's next email in folder
            }
            depth--;
        }
        depth--;
    }

    /**
     * Method that return list of folder names. It assumed to be used on form
     * where will be displayed list of all folders and subfolder.
     *
     * @return
     */
    public List<String> GetFolderList() {
        return folderList;
    }
}

