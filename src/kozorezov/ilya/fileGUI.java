package kozorezov.ilya;

/**
 * This will ask the user to find a file that contain the content of the map that the user will
 * be able to play the game on
 */


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class fileGUI extends JFrame implements ActionListener {

    private static JButton myUpload;
    private static File myMapFile;
    private static boolean myGotFile = false;


    fileGUI(){
        //Set up the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout( new FlowLayout());

        //Ask user to myUpload the map file
        JLabel label = new JLabel("Please select the text file for the map:");
        myUpload = new JButton("Select File");
        myUpload.addActionListener(this);
        this.add(label);
        this.add(myUpload);

        this.setSize(new Dimension(300,100));
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setResizable(false);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myUpload){
            // Have the user find the text file which contains the game map
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);
            int response = chooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                if(file.getName().endsWith(".txt")) {
                    myMapFile = file;
                    myGotFile = true;
                    this.dispose();
                }

            }

        }
    }

    // all the getters
    public static File getMapFile(){
        return myMapFile;
    }
    public static boolean getGotFile(){
        return myGotFile;
    }
}
