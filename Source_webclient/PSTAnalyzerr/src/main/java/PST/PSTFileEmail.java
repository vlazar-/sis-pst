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

                if (email.getActionDate() != null) {
                    jsonDocument.put("repliedDate", email.getActionDate());
                } else {
                    jsonDocument.put("repliedDate", "null");
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
                    jsonDocument.put("senderName", email.getSenderName());
                } else {
                    jsonDocument.put("senderName", "null");
                }

                if (email.getConversationTopic() != null) {
                    jsonDocument.put("subject", email.getConversationTopic());
                } else {
                    jsonDocument.put("subject", "null");
                }

                jsonDocument.put("deletedAfterSubmit", email.getDeleteAfterSubmit());

                if (email.getDisplayTo() != null) {
                    jsonDocument.put("getDisplayTo", email.getDisplayTo());
                } else {
                    jsonDocument.put("getDisplayTo", "null");
                }

                jsonDocument.put("importance", email.getImportance());

                if (email.getInReplyToId() != null) {
                    jsonDocument.put("getInReplyToId", email.getInReplyToId());
                } else {
                    jsonDocument.put("getInReplyToId", "null");
                }

                if (email.getInternetMessageId() != null) {
                    jsonDocument.put("internetMessageId", email.getInternetMessageId());
                } else {
                    jsonDocument.put("internetMessageId", "null");
                }

                jsonDocument.put("getMessageCcMe", email.getMessageCcMe());

                if (email.getMessageDeliveryTime() != null) {
                    jsonDocument.put("messageDeliveryTime", email.getMessageDeliveryTime());
                } else {
                    jsonDocument.put("messageDeliveryTime", "null");
                }

                jsonDocument.put("getMessageSize", email.getMessageSize());

                jsonDocument.put("getMessageToMe", email.getMessageToMe());

                jsonDocument.put("getNumberOfAttachments", email.getNumberOfAttachments());

                jsonDocument.put("getNumberOfRecipients", email.getNumberOfRecipients());

                jsonDocument.put("getOriginalSensitivity",email.getOriginalSensitivity());

                jsonDocument.put("getPriority", email.getPriority());

                if (email.getReplyRecipientNames() != null) {
                    jsonDocument.put("getReplyRecipientNames", email.getReplyRecipientNames());
                } else {
                    jsonDocument.put("getReplyRecipientNames", "null");
                }

                if (email.getSenderEmailAddress() != null) {
                    jsonDocument.put("getSenderEmailAddress", email.getSenderEmailAddress());
                } else {
                    jsonDocument.put("getSenderEmailAddress", "null");
                }

                if (email.getTaskStartDate() != null) {
                    jsonDocument.put("getTaskStartDate", email.getTaskStartDate());
                } else {
                    jsonDocument.put("getTaskDueDate", "null");
                }


                jsonDocument.put("hasForwarded", email.hasForwarded());

                jsonDocument.put("hasReplied", email.hasReplied());

                jsonDocument.put("isRead", email.isRead());

                jsonDocument.put("isResent", email.isResent());

                jsonDocument.put("isUnmodified", email.isUnmodified());

                jsonDocument.put("isUnsent", email.isUnsent());

                if (email.getBodyHTML() != null) {
                    jsonDocument.put("emailBodyHtml", email.getBodyHTML());
                } else {
                    jsonDocument.put("emailBodyHtml", "null");
                }
                if (email.getTransportMessageHeaders() != null) {
                    jsonDocument.put("getTransportMessageHeaders", email.getTransportMessageHeaders());
                } else {
                    jsonDocument.put("getTaskDueDate", "null");
                }

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

