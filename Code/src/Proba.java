import android.util.Log;
import com.pff.PSTException;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by goran on 6.12.2014..
 */
public class Proba {

    int depth=-1;
    public void proccessFolder(PSTFolder folder) throws PSTException, IOException {
        depth++;

        if(depth>0){
           // printDepth();
            //System.out.println("IME FOLDERA:" +folder.getDisplayName()); //ispisuje ime foldera
        }

        if(folder.hasSubfolders()){ ///HHEHEHEHEHE rekurzija :D
            Vector<PSTFolder> childFolders=folder.getSubFolders();
            for (PSTFolder childFolder : childFolders){
                //System.out.println("Ime foldera: "+childFolder.getDisplayName()); //ispisuje ime foldera
                    proccessFolder(childFolder);
            }
        }

        if(folder.getContentCount()>0){
            depth++;
            PSTMessage email= (PSTMessage) folder.getNextChild();
            while (email!=null){
                   // printDepth();
                    /*System.out.println("Subject: " + email.getSubject());
                    System.out.println("Email pošiljetelja: " + email.getSenderName());
                    System.out.println("Email primatelja: " + email.getRcvdRepresentingEmailAddress());*/
                    if(email.getInternetMessageId().length()>2)
                        System.out.println("IME FOLDERA:    " +folder.getDisplayName() + "   EMAIL PRIMATELJA: " + email.getReceivedByAddress() + "   EMAIL POŠILJATELJA: " + email.getSenderName()); //ispisuje ime foldera
                    email = (PSTMessage) folder.getNextChild();
            }
            depth--;
        }
        depth--;
    }

   /* public void printDepth(){
        for(int x=0; x<depth-1; x++){
            System.out.print(" | ");
        }
        System.out.print(" |- ");
    }*/
}
