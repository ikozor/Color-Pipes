package kozorezov.ilya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.LinkedList;


public class GUI extends JFrame implements MouseListener {

    private boolean movable = false;
    private static int color = 0;
    private final Space[][] guiMap;

    private static final LinkedList[] connectedLists = new LinkedList[9];
    private static final boolean[] listsConnected = new boolean[9];

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
        listsConnected[0] = true;
        listsConnected[1] = true;
        listsConnected[2] = true;
        listsConnected[3] = true;
        listsConnected[4] = true;
        listsConnected[5] = true;
        listsConnected[6] = true;
        listsConnected[7] = true;
        listsConnected[8] = true;

        try {
            new inputReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        int[][] map = Map.getMap();
        guiMap = new Space[Map.getX()][Map.getY()];

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(Map.getX(), Map.getY()));
        gamePanel.setPreferredSize(new Dimension(600, 600));

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
        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            clearMap();
            Map.clearMap();
        });
        panel.add(clear);


        this.add(panel);
        this.setVisible(true);


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

    private void clearMap(){
        for (int i = 0; i < Map.getX(); i++) {
            for (int j = 0; j < Map.getY(); j++) {
                if (guiMap[i][j].getType() >= 10) guiMap[i][j].convert(0);
            }
        }
    }

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

    public boolean isListConnected(int color){

        Space first = (Space) connectedLists[color].getFirst();
        Space last = (Space) connectedLists[color].getLast();

        return first.getType() == last.getType() && connectedLists[color].size() > 1 && first != last;
    }

    public void allConnected(){
        if(listsConnected[0] && listsConnected[1] && listsConnected[2] && listsConnected[3] && listsConnected[4] &&
                listsConnected[5] && listsConnected[6] && listsConnected[7] && listsConnected[8]){
            JOptionPane.showMessageDialog(this,"You have connected all the pipes");
            System.exit(0);
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {
        Space initial = (Space) e.getSource();
        if(initial.getType() > 0 && initial.getType() < 10 ) {
            if (!movable) {
                movable = true;
                color = initial.getType() * 10;
                clearColor(color);

                connectedLists[initial.getType()-1].add(initial);
            }
        }
        else if(initial.getType() > 9 && initial.getType() < 100) {
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
        if(passed.getType() > 9 && movable && color != passed.getType()){
            clearColor(passed.getType());
        }

        if(movable && passed.getType() == 0){
            passed.convert(color);
            Map.setPos(passed.getxPos(),passed.getyPos(), color);

            connectedLists[(color/10) -1].add(passed);
        }
        else if(passed.getType() > 0 && passed.getType() < 10 && color != 0 && movable){
            connectedLists[(color/10) -1].add(passed);
            listsConnected[(color/10)-1] = isListConnected((color/10) -1);
            allConnected();
        }
        else movable = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
}


