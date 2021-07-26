package kozorezov.ilya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class fileGUI extends JFrame implements ActionListener {

    private static JButton upload;
    private static File mapFile;
    private static boolean gotFile = false;


    fileGUI(){
        //Set up the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout( new FlowLayout());

        //Ask user to upload the map file
        JLabel label = new JLabel("Please select the text file for the map:");
        upload = new JButton("Select File");
        upload.addActionListener(this);
        this.add(label);
        this.add(upload);

        this.setSize(new Dimension(300,100));
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setResizable(false);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == upload){
            // Have the user find the text file which contains the game map
            JFileChooser chooser = new JFileChooser();
            int response = chooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                if(file.getName().endsWith(".txt")) {
                    mapFile = file;
                    gotFile = true;
                    this.dispose();
                }

            }

        }
    }
    public static File getMapFile(){
        return mapFile;
    }
    public static boolean getGotFile(){
        return gotFile;
    }
}
