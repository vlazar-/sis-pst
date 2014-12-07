import com.pff.PSTFile;

public class Main {

    public static void main(String[] args) {
        //String fileName="D:"+"\\"+"FAKS"+"\\"+"4_CETVRTA_GODINA"+"\\"+"Sigurnost informacijskih sustava"+"\\"+"PST_dat"+"\\"+"mark_whitt" +"\\"+"mark_whitt_000_1_1.pst";
        //String fileName="D:"+"\\"+"FAKS"+"\\"+"4_CETVRTA_GODINA"+"\\"+"Sigurnost informacijskih sustava"+"\\"+"PST_dat"+"\\"+"albert_meyers" +"\\"+"albert_meyers_000_1_1.pst";
        String fileName="D:"+"\\"+"FAKS"+"\\"+"4_CETVRTA_GODINA"+"\\"+"Sigurnost informacijskih sustava"+"\\"+"PST_dat"+"\\"+"goran_fazer@hotmail.com.pst";
        System.out.println(fileName);

        Proba proba=new Proba();

        try{
            PSTFile pstFile=new PSTFile(fileName);
            //System.out.println(pstFile.getMessageStore().getDisplayName());
            proba.proccessFolder(pstFile.getRootFolder());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }




}
