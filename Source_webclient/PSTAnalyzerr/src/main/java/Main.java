import Client.Server;
import ElasticSearch.CreateNode;
import ElasticSearch.DbConnect;
import PST.PSTFileEmail;
import com.pff.PSTFile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;



/**
 * Created by Viktor on 15/12/2014.
 */
public class Main extends JFrame {

    protected JButton btnStart, btnStop;
    protected JLabel labStart;
    private static final int DEFAULT_SIZE = 3;
    protected Dimension dimension = new Dimension(600, 400);


    public Main() {

        JPanel pane = (JPanel) getContentPane();
        pane.setPreferredSize(dimension);
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        labStart = new JLabel();
        System.out.println(labStart.getText());

        btnStart = new JButton("Launch");
        btnStart.setMnemonic(KeyEvent.VK_M);
        btnStart.addActionListener(e -> {
            System.out.println("klik");
            initElasticSearch();
            labStart.setText("  ElasticSearch is up and running  ");
            Server s = new Server();
            s.initServer();
            labStart.setText(labStart.getText() + " -   Go to localhost:4567 ");
        });


        //gl.setAutoCreateContainerGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(labStart))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnStart)
                                .addComponent(labStart))
        );

        pack();
        //center of screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void initElasticSearch() {
        // TODO code application logic here

        String fileName = "D:" + "\\" + "FAKS" + "\\" + "4_CETVRTA_GODINA" + "\\" + "Sigurnost_informacijskih_sustava" + "\\" + "PST_dat" + "\\" + "goran_fazer@hotmail.com.pst";
        System.out.println(fileName);
        CreateNode createNode = CreateNode.getInstance();
        System.out.println("CLIENT DATA MOTHERFUCKER!!!!!: "+createNode.node.client().toString());
        DbConnect db = new DbConnect();

        if(db.CheckIfIndexExists()==true){
            System.out.println("OBRISAN JE INDEKS");
            db.DeleteIndex("pstindex");
        }
        if(db.CheckIfIndexExists()==false) {
            if (db.CreateIndex("pstindex") == true) {
                System.out.println("NAPRAVLJEN JE INDEKS");
            }
        }

        //db.CreateMapping();

        PSTFileEmail fileEmail = new PSTFileEmail();
        try {
            PSTFile pstFile = new PSTFile(fileName);
            fileEmail.proccessFolder(pstFile.getRootFolder());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private static void initGUI() {
        Main m = new Main();
        m.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGUI();
            }
        });
    }
}
