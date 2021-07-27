package kozorezov.ilya;
/**
 * The actual place where the game will be, this also checks to see if all the pipes are connected and if the
 * user finished the game or not
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;


public class GUI extends JFrame implements MouseListener {

    private boolean movable = false;                                            // if the user can set new pipes
    private static int color = 0;                                               // the value of the color that will be placed
    private final Space[][] guiMap;                                             // the map where the game will take place

    private static final LinkedList[] connectedLists = new LinkedList[9];       // A collection of each color to check if the pipe has been connected
    private static final boolean[] listsConnected = new boolean[9];             // declare if a pipe has been connected

    // All of the possible pipes in the game
    private static final LinkedList<Space> redList = new LinkedList<>();
    private static final LinkedList<Space> blueList = new LinkedList<>();
    private static final LinkedList<Space> greenList = new LinkedList<>();
    private static final LinkedList<Space> purpleList = new LinkedList<>();
    private static final LinkedList<Space> yellowList = new LinkedList<>();
    private static final LinkedList<Space> orangeList = new LinkedList<>();
    private static final LinkedList<Space> pinkList = new LinkedList<>();
    private static final LinkedList<Space> grayList = new LinkedList<>();
    private static final LinkedList<Space> whiteList = new LinkedList<>();


    public GUI() {
        // say that all of the lists are connected in case game .txt file does not contain that color
        listsConnected[0] = true;
        listsConnected[1] = true;
        listsConnected[2] = true;
        listsConnected[3] = true;
        listsConnected[4] = true;
        listsConnected[5] = true;
        listsConnected[6] = true;
        listsConnected[7] = true;
        listsConnected[8] = true;

        // get the map from the input
        try {
            new inputReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // create the map from the input
        int[][] map = Map.getMap();
        guiMap = new Space[Map.getX()][Map.getY()];

        // set up the game panel
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(Map.getX(), Map.getY()));
        gamePanel.setPreferredSize(new Dimension(600, 600));

        // draw the game map
        for (int i = 0; i < Map.getX(); i++) {
            for (int j = 0; j < Map.getY(); j++) {
                guiMap[i][j] = new Space(map[i][j], i, j);
                guiMap[i][j].addMouseListener(this);
                gamePanel.add(guiMap[i][j]);
                if(map[i][j] != 0){
                    listsConnected[map[i][j] -1] = false;
                }
            }
        }
        panel.add(gamePanel);

        // create a clear button that clears all of the pipes
        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            clearMap();
            Map.clearMap();
        });
        panel.add(clear);


        this.add(panel);
        this.setVisible(true);

        // add all of the linked list to the array
        connectedLists[0] = redList;
        connectedLists[1] = blueList;
        connectedLists[2] = greenList;
        connectedLists[3] = purpleList;
        connectedLists[4] = yellowList;
        connectedLists[5] = orangeList;
        connectedLists[6] = pinkList;
        connectedLists[7] = grayList;
        connectedLists[8] = whiteList;
    }

    // clear map method
    private void clearMap(){
        for (int i = 0; i < Map.getX(); i++) {
            for (int j = 0; j < Map.getY(); j++) {
                if (guiMap[i][j].getType() >= 10) guiMap[i][j].convert(0);
            }
        }
    }

    // to clear a specific color
    public void clearColor(int color){
        for (int i = 0; i < Map.getX(); i++) {
            for (int j = 0; j < Map.getY(); j++) {
                if (guiMap[i][j].getType() == color) guiMap[i][j].convert(0);
            }
        }
        Map.clearColor(color);
        connectedLists[(color/10)-1].clear();
        listsConnected[(color/10)-1] = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Space initial = (Space) e.getSource();                                  // get the space that was pressed on
        if(initial.getType() > 0 && initial.getType() < 10 ) {       // if the space is a set piece, reset the LL and start the pipe
            if (!movable) {
                movable = true;
                color = initial.getType() * 10;
                clearColor(color);

                connectedLists[initial.getType()-1].add(initial);
            }
        }
        else if(initial.getType() > 9 && initial.getType() < 100) {  // else if clicked on pipe then just continue the pipe
            if (!movable) {
                movable = true;
                color = initial.getType();
                }
            }
        else{
            movable = false;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        movable = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Space passed = (Space) e.getSource();
        if(passed.getType() > 9 && movable && color != passed.getType()){  // if you go on another colored pipe, delete the pipe user went over
            clearColor(passed.getType());
        }

        if(movable && passed.getType() == 0){                               // if moved on black space, place new pipe
            passed.convert(color);
            Map.setPos(passed.getxPos(),passed.getyPos(), color);

            connectedLists[(color/10) -1].add(passed);
        }                                                                                                                                                                                                              //This has been written in secret in hopes that the government wont find it. Here is the secret message: I genuinely really enjoy oatmeal raisin cookies, they are far superior than chocolate chip
        else movable = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
}


