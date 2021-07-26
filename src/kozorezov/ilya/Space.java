package kozorezov.ilya;

import javax.swing.*;
import java.awt.*;

public class Space extends JButton{
    private int type = 0;
    private final int xPos;
    private final int yPos;
    private boolean connected = false;
    
    public Space(int color, int x, int y){
        convert(color);
        xPos = x;
        yPos = y;
    }

    public void convert(int color){
        switch (color) {
            case 1 -> {
                this.type = color;
                this.setBackground(Color.red);
            }
            case 2 -> {
                this.type = color;
                this.setBackground(Color.blue);
            }
            case 3 -> {
                this.type = color;
                this.setBackground(new Color(0,100,0));
            }
            case 4 -> {
                this.type = color;
                this.setBackground(Color.MAGENTA);
            }
            case 5 -> {
                this.type = color;
                this.setBackground(Color.yellow);
            }
            case 6 -> {
                this.type = color;
                this.setBackground(Color.ORANGE);
            }
            case 7 -> {
                this.type = color;
                this.setBackground(Color.PINK);
            }
            case 8 -> {
                this.type = color;
                this.setBackground(Color.GRAY);
            }
            case 9, 90 -> {
                this.type = color;
                this.setBackground(Color.white);
            }
            case 10 -> {
                this.type = color;
                this.setBackground(new Color(255,102,102));
            }
            case 20 -> {
                this.type = color;
                this.setBackground(new Color(0,136,204));
            }
            case 30 -> {
                this.type = color;
                this.setBackground(Color.green);
            }
            case 40 -> {
                this.type = color;
                this.setBackground(new Color(234,128,255));
            }
            case 50 -> {
                this.type = color;
                this.setBackground(new Color(255,255,204));
            }
            case 60 -> {
                this.type = color;
                this.setBackground(new Color(255,212,128));
            }
            case 70 -> {
                this.type = color;
                this.setBackground(new Color(255,153,255));
            }
            case 80 -> {
                this.type = color;
                this.setBackground(new Color(178,190,181));
            }
            default -> {
                this.type = 0;
                this.setBackground(Color.black);
            }
        }
    }

    public int getType() {
        return type;
    }

    public int getyPos() {
        return yPos;
    }
    public int getxPos() {
        return xPos;
    }
}
